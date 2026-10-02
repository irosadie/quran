package com.binarydev.quran.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO Quran Foundation API v4 (https://apis.quran.foundation/content/api/v4).
 * Mushaf Madinah = mushaf=1 (QCF V2, 604 halaman).
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
)
