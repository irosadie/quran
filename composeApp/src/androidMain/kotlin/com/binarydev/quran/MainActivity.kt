package com.binarydev.quran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.binarydev.quran.app.QuranApp
import com.binarydev.quran.platform.AppContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppContext.context = this // sebelum Koin start (DB + DataStore butuh Context)
        super.onCreate(savedInstanceState)
        setContent { QuranApp() }
    }
}
