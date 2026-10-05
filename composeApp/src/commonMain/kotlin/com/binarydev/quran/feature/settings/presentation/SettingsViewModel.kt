package com.binarydev.quran.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsState(val dark: Boolean = false, val arabScale: Float = 1f, val latinShown: Boolean = true)
sealed interface SettingsEvent {
    data class Dark(val on: Boolean) : SettingsEvent
    data class ArabScale(val scale: Float) : SettingsEvent
    data class Latin(val shown: Boolean) : SettingsEvent
}

/** arabScale + latinShown persisten via DataStore; dark masih lokal (menyusul). */
class SettingsViewModel(private val repo: SettingsRepository) : ViewModel(), MviContract<SettingsState, SettingsEvent, UiEffect> {
    private val _state = MutableStateFlow(SettingsState())
    override val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repo.arabFontScale().collect { v -> _state.update { it.copy(arabScale = v) } }
        }
        viewModelScope.launch {
            repo.latinShown().collect { v -> _state.update { it.copy(latinShown = v) } }
        }
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.Dark -> _state.update { it.copy(dark = event.on) }
            is SettingsEvent.ArabScale -> {
                val v = event.scale.coerceIn(0.8f, 2f)
                _state.update { it.copy(arabScale = v) }
                viewModelScope.launch { repo.setArabFontScale(v) }
            }
            is SettingsEvent.Latin -> {
                _state.update { it.copy(latinShown = event.shown) }
                viewModelScope.launch { repo.setLatinShown(event.shown) }
            }
        }
    }
}
