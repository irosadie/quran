package com.binarydev.quran.core.data.repository

import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.data.local.SurahMetadata
import com.binarydev.quran.core.data.mapper.toDomain
import com.binarydev.quran.core.data.remote.api.QuranApi
import com.binarydev.quran.core.domain.model.MushafPage
import com.binarydev.quran.core.domain.model.Surah
import com.binarydev.quran.core.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Offline-first ringan: surah dari bundel, ayat/page dari API + cache memori. */
class QuranRepositoryImpl(private val api: QuranApi) : QuranRepository {
    private val pageCache = mutableMapOf<Int, MushafPage>()
    private val surahCache = mutableMapOf<Int, List<com.binarydev.quran.core.domain.model.Ayah>>()

    override fun surahs(): Flow<List<Surah>> = flow { emit(SurahMetadata.all()) }

    override suspend fun ayahsBySurah(surah: Int): AppResult<List<com.binarydev.quran.core.domain.model.Ayah>> =
        surahCache[surah]?.let { AppResult.Ok(it) } ?: runCatching {
            api.versesByChapter(surah).verses.map { it.toDomain() }
        }.fold(
            onSuccess = { list -> surahCache[surah] = list; AppResult.Ok(list) },
            onFailure = { AppResult.Err("Gagal memuat surah $surah. Cek koneksi.", it) },
        )

    override suspend fun page(pageNumber: Int): AppResult<MushafPage> {
        val p = pageNumber.coerceIn(1, 604)
        pageCache[p]?.let { return AppResult.Ok(it) }
        return runCatching { api.versesByPage(p).verses.map { it.toDomain() } }.fold(
            onSuccess = { list ->
                val m = MushafPage(p, list)
                if (list.isNotEmpty()) pageCache[p] = m
                AppResult.Ok(m)
            },
            onFailure = { AppResult.Err("Gagal memuat halaman $p.", it) },
        )
    }

    override suspend fun search(query: String): AppResult<List<com.binarydev.quran.core.domain.model.Ayah>> =
        runCatching { api.search(query).verses.map { it.toDomain() } }.fold(
            onSuccess = { AppResult.Ok(it) },
            onFailure = { AppResult.Err("Pencarian gagal.", it) },
        )
}
