package com.binarydev.quran.core.data.repository

import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.data.local.SurahMetadata
import com.binarydev.quran.core.data.mapper.toDomain
import com.binarydev.quran.core.data.mapper.toPageLines
import com.binarydev.quran.core.data.remote.api.QuranApi
import com.binarydev.quran.core.domain.model.Ayah
import com.binarydev.quran.core.domain.model.LineSeg
import com.binarydev.quran.core.domain.model.MushafLine
import com.binarydev.quran.core.domain.model.MushafPage
import com.binarydev.quran.core.domain.model.PageLines
import com.binarydev.quran.core.domain.model.Surah
import com.binarydev.quran.core.domain.repository.QuranRepository
import com.binarydev.quran.db.Ayah as AyahRow
import com.binarydev.quran.db.QuranDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/**
 * Offline-first: surah dari bundel, ayat dari SQLite dulu (tulis-sekali-baca-selamanya),
 * network hanya saat miss. Menggantikan cache memori.
 */
class QuranRepositoryImpl(
    private val api: QuranApi,
    db: QuranDatabase,
) : QuranRepository {
    private val q = db.quranQueries

    override fun surahs(): Flow<List<Surah>> = flow { emit(SurahMetadata.all()) }

    override suspend fun ayahsBySurah(surah: Int): AppResult<List<Ayah>> {
        val cached = withContext(Dispatchers.IO) {
            q.ayahsBySurah(surah.toLong()).executeAsList().map { it.toDomain() }
        }
        if (cached.isNotEmpty()) return AppResult.Ok(cached)
        return runCatching {
            api.versesByChapter(surah).verses.map { it.toDomain() }
        }.fold(
            onSuccess = { list -> list.forEach { insert(it) }; AppResult.Ok(list) },
            onFailure = { AppResult.Err("Gagal memuat surah $surah. Cek koneksi.", it) },
        )
    }

    override suspend fun page(pageNumber: Int): AppResult<MushafPage> {
        val p = pageNumber.coerceIn(1, 604)
        val cached = withContext(Dispatchers.IO) {
            q.ayahsByPage(p.toLong()).executeAsList().map { it.toDomain() }
        }
        if (cached.isNotEmpty()) return AppResult.Ok(MushafPage(p, cached))
        return runCatching {
            api.versesByPage(p).verses.map { it.toDomain() }
        }.fold(
            onSuccess = { list ->
                list.forEach { insert(it) }
                AppResult.Ok(MushafPage(p, list))
            },
            onFailure = { AppResult.Err("Gagal memuat halaman $p.", it) },
        )
    }

    override suspend fun search(query: String): AppResult<List<Ayah>> =
        runCatching { api.search(query).search.results.map { it.toDomain() } }.fold(
            onSuccess = { AppResult.Ok(it) },
            onFailure = { AppResult.Err("Pencarian gagal.", it) },
        )

    override suspend fun pageLines(pageNumber: Int): AppResult<PageLines> {
        val p = pageNumber.coerceIn(1, 604)
        val (cachedLines, cachedAyahs) = withContext(Dispatchers.IO) {
            val lines = q.linesByPage(p.toLong()).executeAsList().map {
                MushafLine(it.line.toInt(), segJson.decodeFromString(ListSerializer(LineSeg.serializer()), it.segs))
            }
            val ayahs = q.ayahsByPage(p.toLong()).executeAsList().map { it.toDomain() }
            lines to ayahs
        }
        if (cachedLines.isNotEmpty() && cachedAyahs.isNotEmpty()) {
            return AppResult.Ok(PageLines(p, cachedAyahs.first().juz, cachedLines, cachedAyahs))
        }
        return runCatching { api.versesByPageWords(p).toPageLines(p) }.fold(
            onSuccess = { pl ->
                withContext(Dispatchers.IO) {
                    pl.ayahs.forEach { insert(it) }
                    pl.lines.forEach { line ->
                        q.insertLine(p.toLong(), line.number.toLong(), segJson.encodeToString(ListSerializer(LineSeg.serializer()), line.segs))
                    }
                }
                AppResult.Ok(pl)
            },
            onFailure = { AppResult.Err("Gagal memuat halaman $p.", it) },
        )
    }

    override suspend fun firstPageOf(surah: Int, ayah: Int): AppResult<Int> {
        withContext(Dispatchers.IO) {
            q.firstPageOf(surah.toLong(), ayah.toLong()).executeAsOneOrNull()?.toInt()
        }?.takeIf { it in 1..604 }?.let { return AppResult.Ok(it) }
        return when (val r = ayahsBySurah(surah)) {
            is AppResult.Ok -> r.data.firstOrNull { it.ayah == ayah }?.page
                ?.takeIf { it in 1..604 }
                ?.let { AppResult.Ok(it) }
                ?: AppResult.Err("Ayat $surah:$ayah tidak ketemu.")
            is AppResult.Err -> r
            AppResult.Loading -> AppResult.Loading
        }
    }

    private suspend fun insert(a: Ayah) = withContext(Dispatchers.IO) {
        q.insertAyah(a.surah.toLong(), a.ayah.toLong(), a.key, a.textUthmani, a.juz.toLong(), a.page.toLong())
        q.keepFirstPage(a.page.toLong(), a.key)
    }

    private fun AyahRow.toDomain() = Ayah(
        surah = surah.toInt(), ayah = ayah.toInt(), key = key,
        textUthmani = text_uthmani, juz = juz.toInt(), page = page.toInt(),
    )

    companion object {
        private val segJson = Json { ignoreUnknownKeys = true }
    }
}
