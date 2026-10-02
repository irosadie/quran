package com.binarydev.quran.core.data.mapper

import com.binarydev.quran.core.data.remote.dto.VerseDto
import com.binarydev.quran.core.domain.model.Ayah

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
