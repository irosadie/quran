package com.binarydev.quran.feature.reader.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.binarydev.quran.core.designsystem.MushafText
import org.koin.compose.viewmodel.koinViewModel

/**
 * Layar baca responsif: mode Surah (daftar ayat) & mode Mushaf (halaman 1..604).
 * RTL untuk Arab, pinch-zoom diganti slider font agar ringan & mudah dipakai.
 */
@Composable
fun ReaderScreen(
    surah: Int = 1,
    page: Int? = null,
    vm: ReaderViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    LaunchedEffect(surah, page) {
        if (page != null) vm.onEvent(ReaderEvent.LoadPage(page)) else vm.onEvent(ReaderEvent.LoadSurah(surah))
    }
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { vm.onEvent(ReaderEvent.ToggleMode(ReadMode.SURAH)) }) { Text("Surah") }
            OutlinedButton(onClick = { vm.onEvent(ReaderEvent.LoadPage(state.page)) }) { Text("Mushaf") }
            if (state.mode == ReadMode.MUSHAF) {
                TextButton(onClick = { vm.onEvent(ReaderEvent.NextPage(-1)) }) { Text("‹") }
                Text("Hlm ${state.page}/604", style = MaterialTheme.typography.bodySmall)
                TextButton(onClick = { vm.onEvent(ReaderEvent.NextPage(1)) }) { Text("›") }
            }
        }
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Ukuran huruf", style = MaterialTheme.typography.bodySmall)
            Text("${(state.fontScale * 100).toInt()}%", style = MaterialTheme.typography.bodySmall)
        }
        Slider(
            value = state.fontScale, onValueChange = { vm.onEvent(ReaderEvent.SetFontScale(it)) },
            valueRange = 0.8f..2f, modifier = Modifier.padding(horizontal = 16.dp),
        )
        when {
            state.loading -> CircularProgressIndicator(Modifier.padding(24.dp))
            state.error != null -> Column(Modifier.padding(24.dp)) {
                Text(state.error!!, color = MaterialTheme.colorScheme.error)
                Button(onClick = { vm.onEvent(ReaderEvent.Retry) }) { Text("Coba lagi") }
            }
            else -> LazyColumn(Modifier.fillMaxSize()) {
                items(state.ayahs, key = { it.key }) { ayah ->
                    MushafText("﴿${ayah.ayah}﴾ ${ayah.textUthmani}", fontScale = state.fontScale)
                }
            }
        }
    }
}
