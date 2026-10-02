package com.binarydev.quran.feature.reader.presentation

import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.domain.model.Ayah

/** Dua mode baca: Surah (scroll) & Mushaf (halaman Madinah 1..604). */
enum class ReadMode { SURAH, MUSHAF }

data class ReaderState(
    val mode: ReadMode = ReadMode.SURAH,
    val surah: Int = 1,
    val page: Int = 1,
    val ayahs: List<Ayah> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null,
    val fontScale: Float = 1f,
)

sealed interface ReaderEvent {
    data class LoadSurah(val number: Int) : ReaderEvent
    data class LoadPage(val page: Int) : ReaderEvent
    data class NextPage(val step: Int = 1) : ReaderEvent
    data class ToggleMode(val mode: ReadMode) : ReaderEvent
    data class SetFontScale(val scale: Float) : ReaderEvent
    object Retry : ReaderEvent
}

sealed interface ReaderEffect : UiEffect {
    data class Saved(val key: String) : ReaderEffect
}
