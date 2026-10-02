package com.binarydev.quran.feature.reader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.data.local.InMemoryStore
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.repository.QuranRepository
import com.binarydev.quran.core.domain.usecase.GetMushafPageUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ReaderViewModel(
    private val repo: QuranRepository,
    private val pageUseCase: GetMushafPageUseCase,
    private val store: InMemoryStore,
) : ViewModel(), MviContract<ReaderState, ReaderEvent, ReaderEffect> {
    private val _state = MutableStateFlow(ReaderState())
    override val state: StateFlow<ReaderState> = _state.asStateFlow()
    private val _effect = Channel<ReaderEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    override fun onEvent(event: ReaderEvent) {
        when (event) {
            is ReaderEvent.LoadSurah -> loadSurah(event.number)
            is ReaderEvent.LoadPage -> loadPage(event.page)
            is ReaderEvent.NextPage -> {
                val p = (_state.value.page + event.step).coerceIn(1, 604)
                loadPage(p)
            }
            is ReaderEvent.ToggleMode -> _state.update { it.copy(mode = event.mode) }
            is ReaderEvent.SetFontScale -> _state.update { it.copy(fontScale = event.scale.coerceIn(0.8f, 2f)) }
            ReaderEvent.Retry -> {
                val s = _state.value
                if (s.mode == ReadMode.MUSHAF) loadPage(s.page) else loadSurah(s.surah)
            }
        }
    }

    private fun loadSurah(number: Int) {
        _state.update { it.copy(surah = number, mode = ReadMode.SURAH, loading = true, error = null) }
        viewModelScope.launch {
            when (val r = repo.ayahsBySurah(number)) {
                is AppResult.Ok -> {
                    _state.update { st -> st.copy(ayahs = r.data, loading = false) }
                    r.data.firstOrNull()?.let {
                        store.saveLastRead(Bookmark(it.key, it.surah, it.ayah, createdAt = Clock.System.now().toEpochMilliseconds()))
                    }
                }
                is AppResult.Err -> _state.update { it.copy(loading = false, error = r.message) }
                AppResult.Loading -> Unit
            }
        }
    }

    private fun loadPage(page: Int) {
        _state.update { it.copy(page = page.coerceIn(1, 604), mode = ReadMode.MUSHAF, loading = true, error = null) }
        viewModelScope.launch {
            when (val r = pageUseCase(page)) {
                is AppResult.Ok -> _state.update { st -> st.copy(ayahs = r.data.ayahs, page = r.data.pageNumber, loading = false) }
                is AppResult.Err -> _state.update { it.copy(loading = false, error = r.message) }
                AppResult.Loading -> Unit
            }
        }
    }
}
