package com.binarydev.quran

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.binarydev.quran.app.QuranApp

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Quran Madinah") {
        QuranApp()
    }
}
