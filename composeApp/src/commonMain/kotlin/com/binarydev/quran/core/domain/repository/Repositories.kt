package com.binarydev.quran.core.domain.repository

import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.domain.model.Ayah
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.model.MushafPage
import com.binarydev.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun surahs(): Flow<List<Surah>>
    suspend fun ayahsBySurah(surah: Int): AppResult<List<Ayah>>
    suspend fun page(pageNumber: Int): AppResult<MushafPage>
    suspend fun search(query: String): AppResult<List<Ayah>>
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
