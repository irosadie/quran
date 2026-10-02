package com.binarydev.quran.core.data.local

import com.binarydev.quran.core.domain.model.Revelation
import com.binarydev.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Metadata 114 surah dibundel offline (tanpa network, <10KB).
 * Sumber angka: Tanzil / QuranComplex; arti ringkas Indonesia.
 * TODO(openspec): ganti dengan files/surah_metadata.json + terjemahan Kemenag bila lisensi OK.
 */
object SurahMetadata {
    // number, latin, ayatCount, makkiyah(true)/madaniyah(false) — subset ringkas, dilengkapi bertahap
    private val rows: List<Triple<String, Int, Boolean>> = listOf(
        Triple("Al-Fatihah", 7, true), Triple("Al-Baqarah", 286, false),
        Triple("Ali 'Imran", 200, false), Triple("An-Nisa'", 176, false),
        Triple("Al-Ma'idah", 120, false),
    )

    fun all(): List<Surah> = buildList {
        // 5 pertama akurat; sisanya placeholder agar UI/scroll 114 teruji ringan.
        rows.forEachIndexed { i, (latin, count, makki) ->
            add(
                Surah(
                    number = i + 1,
                    arabicName = "",
                    latinName = latin,
                    arti = "",
                    ayatCount = count,
                    revelation = if (makki) Revelation.MAKKIYAH else Revelation.MADANIYAH,
                ),
            )
        }
        for (n in 6..114) {
            add(Surah(n, "", "Surah $n", "", 0, Revelation.MAKKIYAH))
        }
    }
}

class InMemoryStore {
    private val _lastRead = MutableStateFlow<com.binarydev.quran.core.domain.model.Bookmark?>(null)
    val lastRead: Flow<com.binarydev.quran.core.domain.model.Bookmark?> = _lastRead.asStateFlow()
    suspend fun saveLastRead(b: com.binarydev.quran.core.domain.model.Bookmark) { _lastRead.value = b }
}
