package com.binarydev.quran.app

import com.binarydev.quran.core.data.audio.AudioPlayer
import com.binarydev.quran.core.data.audio.createAudioPlayer
import com.binarydev.quran.core.data.local.InMemoryStore
import com.binarydev.quran.core.data.remote.api.QuranApi
import com.binarydev.quran.core.data.repository.QuranRepositoryImpl
import com.binarydev.quran.core.domain.repository.QuranRepository
import com.binarydev.quran.core.domain.usecase.GetMushafPageUseCase
import com.binarydev.quran.core.domain.usecase.SearchAyahUseCase
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
    single<QuranRepository> { QuranRepositoryImpl(get()) }
    single { InMemoryStore() }
    single<AudioPlayer> { createAudioPlayer() }
    single { GetMushafPageUseCase(get()) }
    single { SearchAyahUseCase(get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ReaderViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::SettingsViewModel)
}
