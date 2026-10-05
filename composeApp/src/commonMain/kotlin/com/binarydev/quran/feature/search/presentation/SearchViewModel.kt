package com.binarydev.quran.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.domain.repository.QuranRepository
import com.binarydev.quran.core.domain.usecase.SearchAyahUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val search: SearchAyahUseCase,
    private val repo: QuranRepository,
) : ViewModel(), MviContract<SearchState, SearchEvent, SearchEffect> {
    private val _state = MutableStateFlow(SearchState())
    override val state: StateFlow<SearchState> = _state.asStateFlow()
    private val _effect = Channel<SearchEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()
    private var job: Job? = null

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Query -> {
                _state.update { it.copy(query = event.text) }
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500) // debounce ringan
                    runSearch()
                }
            }
            SearchEvent.Go -> viewModelScope.launch { runSearch() }
            is SearchEvent.OpenAyah -> viewModelScope.launch { openAyah(event.key) }
        }
    }

    /** Cari halaman pertama ayat, lalu minta navigasi ke sana. */
    private suspend fun openAyah(key: String) {
        val (s, a) = key.split(":").mapNotNull { it.toIntOrNull() }
            .let { it.getOrElse(0) { 0 } to it.getOrElse(1) { 0 } }
        val page = (repo.firstPageOf(s, a) as? AppResult.Ok)?.data
        _effect.send(SearchEffect.NavigateAyah(key, page))
    }

    private suspend fun runSearch() {
        val q = _state.value.query
        if (q.trim().length < 2) {
            _state.update { it.copy(results = emptyList(), loading = false) }
            return
        }
        _state.update { it.copy(loading = true, error = null) }
        when (val r = search(q)) {
            is AppResult.Ok -> _state.update { it.copy(results = r.data, loading = false) }
            is AppResult.Err -> _state.update { it.copy(loading = false, error = r.message) }
            AppResult.Loading -> Unit
        }
    }
}
