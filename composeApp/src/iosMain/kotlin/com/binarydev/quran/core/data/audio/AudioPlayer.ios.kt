package com.binarydev.quran.core.data.audio

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioPlayerDelegateProtocol
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.darwin.NSObject

actual fun createAudioPlayer(): AudioPlayer = IosAudioPlayer()

private class FinishDelegate(private val onFinish: () -> Unit) : NSObject(), AVAudioPlayerDelegateProtocol {
    override fun audioPlayerDidFinishPlaying(player: AVAudioPlayer, successfully: Boolean) {
        onFinish()
    }
}

/**
 * iOS: streaming MP3 langsung via AVAudioPlayer(contentsOfURL:).
 * Semua akses member lewat `?.` (binding AV* di toolchain ini hanya lolos safe-call).
 */
@OptIn(ExperimentalForeignApi::class)
private class IosAudioPlayer : AudioPlayer {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _state = MutableStateFlow<PlayerState>(PlayerState.Idle)
    override val state = _state.asStateFlow()
    private val _events = MutableSharedFlow<PlayerEvent>(extraBufferCapacity = 1)
    override val events = _events.asSharedFlow()

    private var audio: AVAudioPlayer? = null
    private var delegate: FinishDelegate? = null
    private var key = ""

    override suspend fun play(url: String, key: String) {
        stopCurrent()
        this.key = key
        _state.value = PlayerState.Loading(key)
        var av: AVAudioPlayer? = memScoped {
            AVAudioPlayer(contentsOfURL = NSURL(string = url), error = alloc<ObjCObjectVar<NSError?>>().ptr)
        }
        if (av == null) {
            _state.value = PlayerState.Error("Audio gagal diputar.")
            return
        }
        delegate = FinishDelegate {
            scope.launch { _events.emit(PlayerEvent.Finished(key)) }
        }
        av?.delegate = delegate
        av?.prepareToPlay()
        audio = av
        av?.play()
        _state.value = PlayerState.Playing(key)
    }

    override suspend fun pause() {
        audio?.pause()
        _state.value = PlayerState.Paused(key)
    }

    override suspend fun resume() {
        audio?.play()
        _state.value = PlayerState.Playing(key)
    }

    override suspend fun stop() {
        stopCurrent()
        _state.value = PlayerState.Idle
    }

    override fun release() {
        stopCurrent()
        _state.value = PlayerState.Idle
    }

    private fun stopCurrent() {
        audio?.stop()
        audio = null
        delegate = null
    }
}
