package com.binarydev.quran.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import com.binarydev.quran.core.designsystem.ScreenClass
import com.binarydev.quran.core.designsystem.rememberScreenClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.binarydev.quran.core.designsystem.QuranTheme
import com.binarydev.quran.feature.home.presentation.HomeScreen
import com.binarydev.quran.feature.reader.presentation.ReaderScreen
import com.binarydev.quran.feature.search.presentation.SearchScreen
import com.binarydev.quran.feature.settings.presentation.SettingsScreen
import kotlinx.serialization.Serializable
import org.koin.compose.KoinApplication

@Serializable data object Home
@Serializable data class SurahReader(val number: Int)
@Serializable data class PageReader(val page: Int)
@Serializable data object Search
@Serializable data object Settings

/** Root app: Koin + Tema + Navigasi adaptif (bottom bar di HP, rail di tablet/desktop). */
@Composable
fun QuranApp() {
    KoinApplication(application = { modules(appModule) }) {
        QuranTheme {
            val nav = rememberNavController()
            var tab by remember { mutableStateOf("home") }
            val screenClass = rememberScreenClass()
            NavigationSuiteScaffold(
                // HP: bottom-bar; tablet/desktop: rail
                layoutType = when (screenClass) {
                    ScreenClass.EXPANDED, ScreenClass.MEDIUM -> NavigationSuiteType.NavigationRail
                    ScreenClass.COMPACT -> NavigationSuiteType.NavigationBar
                },
                navigationSuiteItems = {
                    item(selected = tab == "home", onClick = { tab = "home"; nav.navigate(Home) }, icon = { Text("⌂") }, label = { Text("Home") })
                    item(selected = tab == "search", onClick = { tab = "search"; nav.navigate(Search) }, icon = { Text("⌕") }, label = { Text("Cari") })
                    item(selected = tab == "settings", onClick = { tab = "settings"; nav.navigate(Settings) }, icon = { Text("⚙") }, label = { Text("Atur") })
                },
            ) {
                NavHost(nav, startDestination = Home, modifier = Modifier.fillMaxSize()) {
                    composable<Home> {
                        HomeScreen(
                            onSurah = { nav.navigate(SurahReader(it)) },
                            onPage = { nav.navigate(PageReader(it)) },
                        )
                    }
                    composable<SurahReader> { backStack ->
                        ReaderScreen(surah = backStack.toRoute<SurahReader>().number)
                    }
                    composable<PageReader> { backStack ->
                        ReaderScreen(page = backStack.toRoute<PageReader>().page)
                    }
                    composable<Search> { SearchScreen(onAyah = {}) }
                    composable<Settings> { SettingsScreen() }
                }
            }
        }
    }
}
