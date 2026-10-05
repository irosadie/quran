package com.binarydev.quran.core.data.local

import com.binarydev.quran.core.domain.model.Revelation
import com.binarydev.quran.core.domain.model.Surah

/**
 * Metadata 114 surah dibundel offline (tanpa network, belasan KB).
 * Angka & nama dari [SurahData]; arti Indonesia menyusul (fase terjemahan).
 */
object SurahMetadata {
    fun all(): List<Surah> = (1..114).map { n ->
        Surah(
            number = n,
            arabicName = SurahData.arabic[n - 1],
            latinName = SurahData.latin[n - 1],
            arti = "",
            ayatCount = SurahData.ayatCount[n - 1],
            revelation = if (n in SurahData.madani) Revelation.MADANIYAH else Revelation.MAKKIYAH,
        )
    }
}
