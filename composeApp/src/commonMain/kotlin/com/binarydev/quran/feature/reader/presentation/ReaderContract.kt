package com.binarydev.quran.feature.reader.presentation

import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.data.audio.Reciter
import com.binarydev.quran.core.data.audio.Reciters
import com.binarydev.quran.core.domain.model.Ayah

/** Dua mode baca: Surah (scroll) & Mushaf (halaman Madinah 1..604). */
enum class ReadMode { SURAH, MUSHAF }

data class ReaderState(
    val mode: ReadMode = ReadMode.SURAH,
    val surah: Int = 1,
    val page: Int = 1,
    val ayahs: List<Ayah> = emptyList(),
    val nextAyahs: List<Ayah> = emptyList(), // halaman berikut (bentangan buku)
    val loading: Boolean = true,
    val error: String? = null,
    val fontScale: Float = 1f,
    // Audio tilawah
    val reciter: Reciter = Reciters.default,
    val audioKey: String? = null, // "surah:ayah" yang sedang berbunyi
    val audioPlaying: Boolean = false,
    val audioLoading: Boolean = false,
)

sealed interface ReaderEvent {
    data class LoadSurah(val number: Int) : ReaderEvent
    data class LoadPage(val page: Int) : ReaderEvent
    data class NextPage(val step: Int = 1) : ReaderEvent
    data class ToggleMode(val mode: ReadMode) : ReaderEvent
    data class SetFontScale(val scale: Float) : ReaderEvent
    object Retry : ReaderEvent
    // Audio tilawah
    data class PlayAyah(val key: String) : ReaderEvent
    data object PlayPage : ReaderEvent
    data object PauseResume : ReaderEvent
    data object StopAudio : ReaderEvent
    data class SelectReciter(val reciter: Reciter) : ReaderEvent
}

sealed interface ReaderEffect : UiEffect {
    data class Saved(val key: String) : ReaderEffect
    data class AudioError(val message: String) : ReaderEffect
}
