package com.binarydev.quran.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import com.binarydev.quran.core.data.audio.AudioPlayer
import com.binarydev.quran.core.data.audio.createAudioPlayer
import com.binarydev.quran.core.data.local.createDatabase
import com.binarydev.quran.core.data.local.createDbDriver
import com.binarydev.quran.core.data.local.createSettingsStore
import com.binarydev.quran.core.data.remote.api.QuranApi
import com.binarydev.quran.core.data.repository.BookmarkRepositoryImpl
import com.binarydev.quran.core.data.repository.QuranRepositoryImpl
import com.binarydev.quran.core.data.local.SettingsRepositoryImpl
import com.binarydev.quran.core.domain.repository.BookmarkRepository
import com.binarydev.quran.core.domain.repository.QuranRepository
import com.binarydev.quran.core.domain.repository.SettingsRepository
import com.binarydev.quran.core.domain.usecase.GetMushafPageUseCase
import com.binarydev.quran.core.domain.usecase.SearchAyahUseCase
import com.binarydev.quran.db.QuranDatabase
import com.binarydev.quran.feature.bookmark.presentation.BookmarkViewModel
import com.binarydev.quran.feature.home.presentation.HomeViewModel
import com.binarydev.quran.feature.reader.presentation.ReaderViewModel
import com.binarydev.quran.feature.search.presentation.SearchViewModel
import com.binarydev.quran.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/** DI ringan dengan Koin — satu graph untuk semua platform. */
val appModule = module {
    single { QuranApi() }
    single<SqlDriver> { createDbDriver() }
    single { createDatabase(get()) }
    single<QuranRepository> { QuranRepositoryImpl(get(), get()) }
    single<DataStore<Preferences>> { createSettingsStore() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get()) }
    single<AudioPlayer> { createAudioPlayer() }
    single { GetMushafPageUseCase(get()) }
    single { SearchAyahUseCase(get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ReaderViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::SettingsViewModel)
}
