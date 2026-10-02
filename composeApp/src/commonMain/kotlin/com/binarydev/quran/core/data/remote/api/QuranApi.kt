package com.binarydev.quran.core.data.remote.api

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
 * Client Mushaf Madinah (QCF V2).
 * Catatan: endpoint foundation butuh x-auth-token + x-client-id untuk skala produksi.
 * Untuk MVP ringan: baseUrl dapat diganti proxy sendiri / file bundel offline.
 * Lihat: https://api-docs.quran.foundation/docs/tutorials/fonts/page-layout/
 */
class QuranApi(
    private val baseUrl: String = "https://apis.quran.foundation/content/api/v4",
) {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }
    private val client = HttpClient(engine()) {
        install(ContentNegotiation) { json(json) }
        install(Logging) { level = LogLevel.NONE } // ringan: matikan log di rilis
    }

    /** Ayat per surah, script Utsmani. words=true agar dapat page_number per kata bila perlu. */
    suspend fun versesByChapter(chapter: Int, perPage: Int = 50): VersesResponse =
        client.get("$baseUrl/verses/by_chapter/$chapter") {
            parameter("words", false)
            parameter("text_uthmani", true)
            parameter("per_page", perPage)
            parameter("mushaf", 1) // 1 = QCF V2 (Mushaf Madinah)
        }.body()

    /** Ayat per halaman Mushaf Madinah 1..604 — inti mode "Mushaf". */
    suspend fun versesByPage(page: Int): VersesResponse =
        client.get("$baseUrl/verses/by_page/$page") {
            parameter("words", false)
            parameter("text_uthmani", true)
            parameter("mushaf", 1)
        }.body()

    suspend fun search(query: String): VersesResponse =
        client.get("$baseUrl/search") {
            parameter("q", query)
            parameter("size", 20)
        }.body()
}
