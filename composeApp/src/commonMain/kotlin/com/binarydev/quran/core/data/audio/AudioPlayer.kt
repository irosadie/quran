package com.binarydev.quran.core.data.audio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/** Status pemutar — dicerminkan ViewModel ke UiState (UDF). */
sealed interface PlayerState {
    data object Idle : PlayerState
    data class Loading(val key: String) : PlayerState
    data class Playing(val key: String) : PlayerState
    data class Paused(val key: String) : PlayerState
    data class Error(val message: String) : PlayerState
}

/** Event sekali-putar dari pemutar (selesai satu trek). */
sealed interface PlayerEvent {
    data class Finished(val key: String) : PlayerEvent
}

/** Kontrak pemutar audio lintas platform. key = "surah:ayah" untuk sorotan UI. */
interface AudioPlayer {
    val state: StateFlow<PlayerState>
    val events: Flow<PlayerEvent>
    suspend fun play(url: String, key: String)
    suspend fun pause()
    suspend fun resume()
    suspend fun stop()
    fun release()
}

/** expect/actual: MediaPlayer (Android), AVPlayer (iOS), stub (Desktop). */
expect fun createAudioPlayer(): AudioPlayer
