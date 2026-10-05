package com.binarydev.quran.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.domain.repository.BookmarkRepository
import com.binarydev.quran.core.domain.repository.QuranRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** MVVM+UDF: satu StateFlow, event -> reduce, effect sekali-pakai. */
class HomeViewModel(
    private val repo: QuranRepository,
    private val bookmarks: BookmarkRepository,
) : ViewModel(), MviContract<HomeState, HomeEvent, HomeEffect> {
    private val _state = MutableStateFlow(HomeState())
    override val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            repo.surahs().collect { list ->
                _state.update { it.copy(surahs = list, loading = false) }
            }
        }
        viewModelScope.launch {
            bookmarks.lastRead().collect { b -> _state.update { it.copy(lastRead = b) } }
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Search -> _state.update { it.copy(query = event.query) }
            is HomeEvent.OpenSurah -> viewModelScope.launch { _effect.send(HomeEffect.NavigateSurah(event.number)) }
            is HomeEvent.OpenPage -> viewModelScope.launch { _effect.send(HomeEffect.NavigatePage(event.page)) }
        }
    }
}
