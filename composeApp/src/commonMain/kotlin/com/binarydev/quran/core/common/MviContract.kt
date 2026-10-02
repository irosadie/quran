package com.binarydev.quran.core.common

import kotlinx.coroutines.flow.StateFlow

/**
 * Kontrak UDF (Unidirectional Data Flow) untuk semua fitur.
 * MVVM: ViewModel memegang [UiState], menerima [UiEvent], memancarkan [UiEffect] sekali-pakai.
 */
interface MviContract<State, Event, Effect> {
    val state: StateFlow<State>
    fun onEvent(event: Event)
}

/** Effect sekali-pakai: navigasi, snackbar, haptic. Dikonsumsi View via Channel. */
interface UiEffect
