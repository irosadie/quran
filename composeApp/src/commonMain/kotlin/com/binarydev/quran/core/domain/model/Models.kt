package com.binarydev.quran.core.domain.model

import kotlinx.serialization.Serializable

/** Metadata surah — dibundel offline (ringan, 114 baris) agar home instan tanpa network. */
@Serializable
data class Surah(
    val number: Int,
    val arabicName: String,
    val latinName: String,
    val arti: String,
    val ayatCount: Int,
    val revelation: Revelation, // Makkiyah / Madaniyah
)

enum class Revelation { MAKKIYAH, MADANIYAH }

/** Satu ayat Mushaf Utsmani (teks Madinah, QCF V2). */
@Serializable
data class Ayah(
    val surah: Int,
    val ayah: Int,
    val key: String, // "2:255"
    val textUthmani: String,
    val juz: Int = 0,
    val page: Int = 0, // halaman Mushaf Madinah 1..604
)

/** Satu halaman Mushaf Madinah (604 halaman, 15 baris). */
data class MushafPage(
    val pageNumber: Int, // 1..604
    val ayahs: List<Ayah>,
)

/** Bookmark / terakhir dibaca. */
@Serializable
data class Bookmark(
    val key: String,
    val surah: Int,
    val ayah: Int,
    val note: String = "",
    val createdAt: Long = 0L,
)
