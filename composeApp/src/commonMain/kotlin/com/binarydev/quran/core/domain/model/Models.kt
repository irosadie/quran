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

/** Satu segmen kata dalam baris + kunci ayat pemiliknya (untuk tap & sorotan). */
@Serializable
data class LineSeg(val t: String, val k: String)

/** Satu baris cetakan (mis. baris 3..8 di halaman bersurah-header). */
data class MushafLine(val number: Int, val segs: List<LineSeg>)

/** Isi halaman dalam tata baris eksak + daftar ayat (untuk audio & info). */
data class PageLines(
    val page: Int,
    val juz: Int,
    val lines: List<MushafLine>,
    val ayahs: List<Ayah>,
) {
    /** Kunci ayat berurutan kemunculan (untuk antrean audio). */
    val keysInOrder: List<String>
        get() = lines.flatMap { it.segs }.map { it.k }.distinct()
}

/** Bookmark / terakhir dibaca. */
@Serializable
data class Bookmark(
    val key: String,
    val surah: Int,
    val ayah: Int,
    val note: String = "",
    val createdAt: Long = 0L,
)
