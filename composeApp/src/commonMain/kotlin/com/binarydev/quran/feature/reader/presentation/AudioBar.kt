package com.binarydev.quran.feature.reader.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.binarydev.quran.core.data.audio.Reciters

/**
 * Bilah audio tilawah: putar halaman, jeda/lanjut, berhenti, ganti qari.
 * Selalu terlihat di mode Mushaf agar mudah dipakai.
 */
@Composable
fun AudioBar(state: ReaderState, onEvent: (ReaderEvent) -> Unit, modifier: Modifier = Modifier) {
    Card(modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            if (state.audioLoading) {
                CircularProgressIndicator(Modifier.size(28.dp).padding(4.dp), strokeWidth = 2.dp)
            } else {
                TextButton(onClick = {
                    if (state.audioPlaying || state.audioKey != null) onEvent(ReaderEvent.PauseResume)
                    else onEvent(ReaderEvent.PlayPage)
                }) { Text(if (state.audioPlaying) "⏸" else "▶") }
            }
            if (state.audioKey != null) {
                TextButton(onClick = { onEvent(ReaderEvent.StopAudio) }) { Text("⏹") }
            }
            Text(
                state.audioKey ?: "Audio tilawah",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f),
            )
            TextButton(onClick = {
                val all = Reciters.all
                val next = all[(all.indexOf(state.reciter) + 1) % all.size]
                onEvent(ReaderEvent.SelectReciter(next))
            }) { Text("🎙 ${state.reciter.short}") }
        }
    }
}
