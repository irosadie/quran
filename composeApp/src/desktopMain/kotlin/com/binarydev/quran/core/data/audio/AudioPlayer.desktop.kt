package com.binarydev.quran.core.data.audio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow

actual fun createAudioPlayer(): AudioPlayer = DesktopAudioPlayer()

/** Stub Desktop: audio menyusul — putar mengembalikan pesan lewat state Error. */
private class DesktopAudioPlayer : AudioPlayer {
    private val _state = MutableStateFlow<PlayerState>(PlayerState.Idle)
    override val state = _state.asStateFlow()
    override val events: Flow<PlayerEvent> = emptyFlow()

    override suspend fun play(url: String, key: String) {
        _state.value = PlayerState.Error("Audio belum tersedia di Desktop (Android/iOS saja).")
    }

    override suspend fun pause() = Unit
    override suspend fun resume() = Unit
    override suspend fun stop() {
        _state.value = PlayerState.Idle
    }

    override fun release() = Unit
}
