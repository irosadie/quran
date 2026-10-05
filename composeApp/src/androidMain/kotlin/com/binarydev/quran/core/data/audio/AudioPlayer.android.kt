package com.binarydev.quran.core.data.audio

import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual fun createAudioPlayer(): AudioPlayer = AndroidAudioPlayer()

/** MediaPlayer satu trek — semua panggilan di Main agar konsisten. */
private class AndroidAudioPlayer : AudioPlayer {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _state = MutableStateFlow<PlayerState>(PlayerState.Idle)
    override val state = _state.asStateFlow()
    private val _events = MutableSharedFlow<PlayerEvent>(extraBufferCapacity = 1)
    override val events = _events.asSharedFlow()

    private var player: MediaPlayer? = null
    private var key = ""

    override suspend fun play(url: String, key: String) = withContext(Dispatchers.Main) {
        try {
            releasePlayer()
            this@AndroidAudioPlayer.key = key
            _state.value = PlayerState.Loading(key)
            val mp = MediaPlayer()
            player = mp
            mp.setOnPreparedListener { it.start(); _state.value = PlayerState.Playing(key) }
            mp.setOnCompletionListener { scope.launch { _events.emit(PlayerEvent.Finished(key)) } }
            mp.setOnErrorListener { _, _, _ ->
                _state.value = PlayerState.Error("Audio gagal diputar.")
                true
            }
            mp.setDataSource(url)
            mp.prepareAsync()
        } catch (t: Throwable) {
            _state.value = PlayerState.Error("Audio gagal diputar.")
        }
    }

    override suspend fun pause() = withContext(Dispatchers.Main) {
        player?.takeIf { it.isPlaying }?.pause()
        _state.value = PlayerState.Paused(key)
    }

    override suspend fun resume() = withContext(Dispatchers.Main) {
        player?.start()
        _state.value = PlayerState.Playing(key)
    }

    override suspend fun stop() = withContext(Dispatchers.Main) {
        releasePlayer()
        _state.value = PlayerState.Idle
    }

    override fun release() {
        releasePlayer()
        _state.value = PlayerState.Idle
    }

    private fun releasePlayer() {
        try {
            player?.reset()
            player?.release()
        } catch (_: Throwable) {
        }
        player = null
    }
}
