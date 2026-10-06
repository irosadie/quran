package com.binarydev.quran.core.data.mapper

import com.binarydev.quran.core.data.remote.dto.SearchResultDto
import com.binarydev.quran.core.data.remote.dto.VerseDto
import com.binarydev.quran.core.data.remote.dto.VersesResponse
import com.binarydev.quran.core.domain.model.Ayah
import com.binarydev.quran.core.domain.model.LineSeg
import com.binarydev.quran.core.domain.model.MushafLine
import com.binarydev.quran.core.domain.model.PageLines

fun VerseDto.toDomain(): Ayah {
    val (s, a) = verseKey.split(":").mapNotNull { it.toIntOrNull() }
        .let { it.getOrElse(0) { chapterId } to it.getOrElse(1) { verseNumber } }
    return Ayah(
        surah = s,
        ayah = a,
        key = verseKey.ifBlank { "$s:$a" },
        textUthmani = textUthmani,
        juz = juzNumber,
        page = pageNumber,
    )
}

/** Hasil /search: hanya verse_key + teks (tanpa juz/page) — cukup untuk daftar hasil. */
fun SearchResultDto.toDomain(): Ayah {
    val (s, a) = verseKey.split(":").mapNotNull { it.toIntOrNull() }
        .let { it.getOrElse(0) { 0 } to it.getOrElse(1) { 0 } }
    return Ayah(surah = s, ayah = a, key = verseKey, textUthmani = text)
}

/**
 * Kelompokkan kata per line_number API menjadi baris cetakan eksak
 * (header surah menempati baris tanpa kata — tidak ada di data).
 */
fun VersesResponse.toPageLines(page: Int): PageLines {
    val ayahs = verses.map { it.toDomain() }
    val juz = verses.firstOrNull()?.juzNumber ?: 0
    val byLine = mutableMapOf<Int, MutableList<LineSeg>>()
    for (v in verses) {
        for (w in v.words) {
            if (w.textUthmani.isBlank() || w.lineNumber <= 0) continue
            // Penanda akhir ayat: digit Arab polos (١٢٣) — tanpa kurung/medali.
            // (KFGQPC tak punya ﴿﴾ = fallback jelek; medali ۝ tak bisa
            // menampung digit dalam teks biasa.)
            byLine.getOrPut(w.lineNumber) { mutableListOf() }
                .add(LineSeg(w.textUthmani, v.verseKey))
        }
    }
    val lines = byLine.keys.sorted().map { n -> MushafLine(n, byLine.getValue(n)) }
    return PageLines(page, juz, lines, ayahs)
}
