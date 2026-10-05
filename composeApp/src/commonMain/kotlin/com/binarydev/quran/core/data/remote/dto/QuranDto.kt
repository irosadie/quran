package com.binarydev.quran.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO Quran.com API v4 publik (https://api.quran.com/api/v4, tanpa auth).
 * Mushaf Madinah = default untuk by_page (604 halaman).
 * Teks Utsmani diminta eksplisit via `fields=text_uthmani`.
 */
@Serializable
data class VersesResponse(
    val verses: List<VerseDto> = emptyList(),
    @SerialName("pagination") val pagination: PaginationDto? = null,
)

@Serializable
data class PaginationDto(
    @SerialName("total_pages") val totalPages: Int = 0,
    @SerialName("total_records") val totalRecords: Int = 0,
)

@Serializable
data class VerseDto(
    @SerialName("verse_key") val verseKey: String = "",
    @SerialName("text_uthmani") val textUthmani: String = "",
    @SerialName("page_number") val pageNumber: Int = 0,
    @SerialName("juz_number") val juzNumber: Int = 0,
    @SerialName("chapter_id") val chapterId: Int = 0,
    @SerialName("verse_number") val verseNumber: Int = 0,
    @SerialName("words") val words: List<WordDto> = emptyList(),
)

/** Satu kata + posisi baris cetakan (line_number) — kunci tata 15 baris eksak. */
@Serializable
data class WordDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("position") val position: Int = 0,
    @SerialName("char_type_name") val charType: String = "word", // word | end | pause
    @SerialName("text_uthmani") val textUthmani: String = "",
    @SerialName("page_number") val pageNumber: Int = 0,
    @SerialName("line_number") val lineNumber: Int = 0,
    @SerialName("v2_page") val v2Page: Int = 0,
)

/** Respons /search: bentuknya beda — bungkus `search.results`. */
@Serializable
data class SearchResponse(val search: SearchBodyDto = SearchBodyDto())

@Serializable
data class SearchBodyDto(val results: List<SearchResultDto> = emptyList())

@Serializable
data class SearchResultDto(
    @SerialName("verse_key") val verseKey: String = "",
    @SerialName("verse_id") val verseId: Int = 0,
    @SerialName("text") val text: String = "",
)
