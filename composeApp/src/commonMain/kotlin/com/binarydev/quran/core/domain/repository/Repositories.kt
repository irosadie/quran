package com.binarydev.quran.core.domain.repository

import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.domain.model.Ayah
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.model.MushafPage
import com.binarydev.quran.core.domain.model.PageLines
import com.binarydev.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun surahs(): Flow<List<Surah>>
    suspend fun ayahsBySurah(surah: Int): AppResult<List<Ayah>>
    suspend fun page(pageNumber: Int): AppResult<MushafPage>
    /** Halaman dalam tata baris cetakan eksak (DB dulu, fetch kata bila miss). */
    suspend fun pageLines(pageNumber: Int): AppResult<PageLines>
    suspend fun search(query: String): AppResult<List<Ayah>>
    /**
     * Halaman PERTAMA kemunculan ayat (ayat panjang bersambung ke halaman
     * berikut; by_chapter mengembalikan page_number awal). Untuk navigasi
     * tepat dari hasil cari/bookmark.
     */
    suspend fun firstPageOf(surah: Int, ayah: Int): AppResult<Int>
}

interface BookmarkRepository {
    fun bookmarks(): Flow<List<Bookmark>>
    suspend fun toggle(bookmark: Bookmark)
    fun lastRead(): Flow<Bookmark?>
    suspend fun setLastRead(bookmark: Bookmark)
}

interface SettingsRepository {
    fun arabFontScale(): Flow<Float>
    suspend fun setArabFontScale(scale: Float)
    fun latinShown(): Flow<Boolean>
    suspend fun setLatinShown(shown: Boolean)
}
