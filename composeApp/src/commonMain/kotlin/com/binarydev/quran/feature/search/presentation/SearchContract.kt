package com.binarydev.quran.feature.search.presentation

import com.binarydev.quran.core.common.UiEffect
import com.binarydev.quran.core.domain.model.Ayah

data class SearchState(
    val query: String = "",
    val results: List<Ayah> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)

sealed interface SearchEvent {
    data class Query(val text: String) : SearchEvent
    object Go : SearchEvent
    data class OpenAyah(val key: String) : SearchEvent
}

sealed interface SearchEffect : UiEffect {
    /** Buka ayat di halaman PERTAMA kemunculannya (null = fallback mode Surah). */
    data class NavigateAyah(val key: String, val page: Int?) : SearchEffect
}
