package com.binarydev.quran.feature.bookmark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BookmarkState(val items: List<Bookmark> = emptyList(), val lastRead: Bookmark? = null)
sealed interface BookmarkEvent { data class Toggle(val b: Bookmark) : BookmarkEvent }

class BookmarkViewModel(private val repo: BookmarkRepository) : ViewModel(), MviContract<BookmarkState, BookmarkEvent, UiEffect> {
    private val _state = MutableStateFlow(BookmarkState())
    override val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repo.bookmarks().collect { list -> _state.update { it.copy(items = list) } }
        }
        viewModelScope.launch {
            repo.lastRead().collect { b -> _state.update { it.copy(lastRead = b) } }
        }
    }

    override fun onEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.Toggle -> viewModelScope.launch { repo.toggle(event.b) }
        }
    }
}
