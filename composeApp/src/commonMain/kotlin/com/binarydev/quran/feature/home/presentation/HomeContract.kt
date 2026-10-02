package com.binarydev.quran.feature.home.presentation

import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.model.Surah

data class HomeState(
    val surahs: List<Surah> = emptyList(),
    val lastRead: Bookmark? = null,
    val loading: Boolean = true,
    val query: String = "",
) {
    val filtered: List<Surah>
        get() = if (query.isBlank()) surahs
        else surahs.filter {
            it.latinName.contains(query, true) || it.number.toString() == query.trim()
        }
}

sealed interface HomeEvent {
    data class Search(val query: String) : HomeEvent
    data class OpenSurah(val number: Int) : HomeEvent
    data class OpenPage(val page: Int) : HomeEvent
}

sealed interface HomeEffect : UiEffect {
    data class NavigateSurah(val number: Int) : HomeEffect
    data class NavigatePage(val page: Int) : HomeEffect
}
