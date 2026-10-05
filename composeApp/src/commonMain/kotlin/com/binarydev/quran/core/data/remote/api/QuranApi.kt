package com.binarydev.quran.core.data.remote.api

import com.binarydev.quran.core.data.remote.dto.SearchResponse
import com.binarydev.quran.core.data.remote.dto.VersesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Client Mushaf Madinah via Quran.com API v4 publik (tanpa auth).
 * by_page = halaman Mushaf Madinah 1..604 (default).
 * Teks Utsmani via `fields=text_uthmani`.
 */
class QuranApi(
    private val baseUrl: String = "https://api.quran.com/api/v4",
) {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }
    private val client = HttpClient(engine()) {
        install(ContentNegotiation) { json(json) }
        install(Logging) { level = LogLevel.NONE } // ringan: matikan log di rilis
    }

    /** Ayat per surah — per_page=300 agar surah terpanjang (286 ayat) cukup 1 request. */
    suspend fun versesByChapter(chapter: Int): VersesResponse =
        client.get("$baseUrl/verses/by_chapter/$chapter") {
            parameter("words", false)
            parameter("fields", "text_uthmani")
            parameter("per_page", 300)
        }.body()

    /** Ayat per halaman Mushaf Madinah 1..604 — inti mode "Mushaf". */
    suspend fun versesByPage(page: Int): VersesResponse =
        client.get("$baseUrl/verses/by_page/$page") {
            parameter("words", false)
            parameter("fields", "text_uthmani")
        }.body()

    suspend fun search(query: String): SearchResponse =
        client.get("$baseUrl/search") {
            parameter("q", query)
            parameter("size", 20)
        }.body()
}
