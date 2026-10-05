package com.binarydev.quran.feature.reader.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.binarydev.quran.core.designsystem.MushafText
import com.binarydev.quran.core.designsystem.ScreenClass
import com.binarydev.quran.core.designsystem.rememberIsLandscape
import com.binarydev.quran.core.designsystem.rememberScreenClass
import org.koin.compose.viewmodel.koinViewModel

/**
 * Layar baca: mode Surah (daftar ayat) & mode Mushaf ala buku
 * (pager RTL 604 halaman; landscape lebar = bentangan 2 halaman + audio).
 */
@Composable
fun ReaderScreen(
    surah: Int = 1,
    page: Int? = null,
    vm: ReaderViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }
    LaunchedEffect(surah, page) {
        if (page != null) vm.onEvent(ReaderEvent.LoadPage(page)) else vm.onEvent(ReaderEvent.LoadSurah(surah))
    }
    LaunchedEffect(vm) {
        vm.effect.collect { e ->
            if (e is ReaderEffect.AudioError) snackbar.showSnackbar(e.message)
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(snackbar) }) { inner ->
        Column(Modifier.fillMaxSize().padding(inner)) {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { vm.onEvent(ReaderEvent.ToggleMode(ReadMode.SURAH)) }) { Text("Surah") }
                OutlinedButton(onClick = { vm.onEvent(ReaderEvent.LoadPage(state.page)) }) { Text("Mushaf") }
                if (state.mode == ReadMode.MUSHAF && !isSpread()) {
                    TextButton(onClick = { vm.onEvent(ReaderEvent.NextPage(-1)) }) { Text("‹") }
                    Text(
                        "Hlm ${state.page}/604",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
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
                state.mode == ReadMode.SURAH -> LazyColumn(Modifier.fillMaxSize()) {
                    items(state.ayahs, key = { it.key }) { ayah ->
                        MushafText("﴿${ayah.ayah}﴾ ${ayah.textUthmani}", fontScale = state.fontScale)
                    }
                }
                else -> {
                    AudioBar(state, vm::onEvent)
                    if (isSpread()) SpreadBook(state, onEvent = vm::onEvent) else MushafPager(state, onEvent = vm::onEvent)
                }
            }
        }
    }
}

@Composable
private fun isSpread(): Boolean =
    rememberScreenClass() == ScreenClass.EXPANDED && rememberIsLandscape()

/** Pager buku: geser seperti membalik halaman (arah RTL). */
@Composable
private fun MushafPager(state: ReaderState, onEvent: (ReaderEvent) -> Unit) {
    val pager = rememberPagerState(initialPage = (state.page - 1).coerceIn(0, 603), pageCount = { 604 })
    LaunchedEffect(state.page) {
        val target = (state.page - 1).coerceIn(0, 603)
        if (pager.currentPage != target) pager.scrollToPage(target)
    }
    LaunchedEffect(pager.currentPage) {
        val p = pager.currentPage + 1
        if (p != state.page) onEvent(ReaderEvent.LoadPage(p))
    }
    HorizontalPager(state = pager, reverseLayout = true, modifier = Modifier.fillMaxSize()) { index ->
        if (index + 1 == state.page) {
            MushafPageView(
                pageNumber = state.page,
                ayahs = state.ayahs,
                fontScale = state.fontScale,
                playingKey = state.audioKey,
                onAyahTap = { onEvent(ReaderEvent.PlayAyah(it)) },
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

/** Bentangan 2 halaman untuk layar lebar landscape — kanan halaman ganjil. */
@Composable
private fun SpreadBook(state: ReaderState, onEvent: (ReaderEvent) -> Unit) {
    val pairStart = ((state.page - 1) / 2 * 2 + 1).coerceIn(1, 603)
    val rightAyahs = if (pairStart == state.page) state.ayahs else state.nextAyahs
    val leftAyahs = if (pairStart == state.page) state.nextAyahs else state.ayahs
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { onEvent(ReaderEvent.LoadPage((pairStart + 2).coerceIn(1, 604))) }) { Text("‹ Berikutnya") }
            Text("Hlm $pairStart–${pairStart + 1}/604", style = MaterialTheme.typography.bodySmall)
            TextButton(onClick = { onEvent(ReaderEvent.LoadPage((pairStart - 2).coerceIn(1, 604))) }) { Text("Sebelumnya ›") }
        }
        Row(Modifier.fillMaxSize()) {
            // Kiri = halaman genap (berikutnya), Kanan = halaman ganjil — seperti buku Arab.
            MushafPageView(
                pageNumber = pairStart + 1,
                ayahs = leftAyahs,
                fontScale = state.fontScale,
                playingKey = state.audioKey,
                onAyahTap = { onEvent(ReaderEvent.PlayAyah(it)) },
                modifier = Modifier.weight(1f).fillMaxSize(),
            )
            MushafPageView(
                pageNumber = pairStart,
                ayahs = rightAyahs,
                fontScale = state.fontScale,
                playingKey = state.audioKey,
                onAyahTap = { onEvent(ReaderEvent.PlayAyah(it)) },
                modifier = Modifier.weight(1f).fillMaxSize(),
            )
        }
    }
}
