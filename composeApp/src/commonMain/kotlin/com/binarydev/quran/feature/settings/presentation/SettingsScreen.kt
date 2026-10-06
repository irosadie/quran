package com.binarydev.quran.feature.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(vm: SettingsViewModel = koinViewModel()) {
    val s by vm.state.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row { Text("Mode malam (nyaman dibaca)", Modifier.weight(1f)); Switch(s.dark, { vm.onEvent(SettingsEvent.Dark(it)) }) }
        Text("Ukuran huruf Arab: ${(s.arabScale * 100).toInt()}%")
        Slider(s.arabScale, { vm.onEvent(SettingsEvent.ArabScale(it)) }, valueRange = 0.8f..2f)
        Row { Text("Tampilkan latin/arti", Modifier.weight(1f)); Switch(s.latinShown, { vm.onEvent(SettingsEvent.Latin(it)) }) }
        Text("Teks Arab: KFGQPC Uthman Taha Naskh (King Fahd Quran Complex). Audio: everyayah.com.", Modifier.padding(top = 16.dp))
    }
}
