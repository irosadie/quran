package com.binarydev.quran.feature.settings.presentation

import androidx.lifecycle.ViewModel
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.common.UiEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(val dark: Boolean = false, val arabScale: Float = 1f, val latinShown: Boolean = true)
sealed interface SettingsEvent {
    data class Dark(val on: Boolean) : SettingsEvent
    data class ArabScale(val scale: Float) : SettingsEvent
    data class Latin(val shown: Boolean) : SettingsEvent
}

class SettingsViewModel : ViewModel(), MviContract<SettingsState, SettingsEvent, UiEffect> {
    private val _state = MutableStateFlow(SettingsState())
    override val state: StateFlow<SettingsState> = _state.asStateFlow()
    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.Dark -> _state.update { it.copy(dark = event.on) }
            is SettingsEvent.ArabScale -> _state.update { it.copy(arabScale = event.scale.coerceIn(0.8f, 2f)) }
            is SettingsEvent.Latin -> _state.update { it.copy(latinShown = event.shown) }
        }
    }
}
