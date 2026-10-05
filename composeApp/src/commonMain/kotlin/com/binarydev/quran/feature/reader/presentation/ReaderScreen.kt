package com.binarydev.quran.feature.reader.presentation

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.binarydev.quran.core.data.local.SurahData
import com.binarydev.quran.core.designsystem.MushafText
import com.binarydev.quran.core.designsystem.ScreenClass
import com.binarydev.quran.core.designsystem.rememberIsLandscape
import com.binarydev.quran.core.designsystem.rememberScreenClass
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.feature.bookmark.presentation.BookmarkEvent
import com.binarydev.quran.feature.bookmark.presentation.BookmarkViewModel
import kotlinx.datetime.Clock
import org.koin.compose.viewmodel.koinViewModel

/**
 * Layar baca: mode Surah (daftar ayat) & mode Mushaf ala buku
 * (pager RTL 604 halaman; landscape lebar = bentangan 2 halaman + audio).
 */
@Composable
fun ReaderScreen(
    surah: Int = 1,
    page: Int? = null,
    onBack: () -> Unit = {},
    onOpenMushaf: (Int) -> Unit = {},
    vm: ReaderViewModel = koinViewModel(),
    bookmarkVm: BookmarkViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val bookmarkState by bookmarkVm.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }
    LaunchedEffect(surah, page) {
        if (page != null) vm.onEvent(ReaderEvent.LoadPage(page)) else vm.onEvent(ReaderEvent.LoadSurah(surah))
    }
    LaunchedEffect(vm) {
        vm.effect.collect { e ->
            when (e) {
                is ReaderEffect.AudioError -> snackbar.showSnackbar(e.message)
                is ReaderEffect.Message -> snackbar.showSnackbar(e.text)
                is ReaderEffect.NavigatePage -> onOpenMushaf(e.page)
                else -> Unit
            }
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(snackbar) }) { inner ->
        Column(Modifier.fillMaxSize().padding(inner)) {
            if (state.mode == ReadMode.MUSHAF) {
                val first = state.ayahs.firstOrNull()
                MushafTopBar(
                    surahLatin = first?.let { SurahData.latin.getOrElse(it.surah - 1) { "" } } ?: "",
                    page = state.page,
                    juz = first?.juz ?: 0,
                    bookmarked = first?.let { a -> bookmarkState.items.any { it.key == a.key } } == true,
                    onBack = onBack,
                    onBookmark = {
                        first?.let {
                            bookmarkVm.onEvent(
                                BookmarkEvent.Toggle(
                                    Bookmark(it.key, it.surah, it.ayah, createdAt = Clock.System.now().toEpochMilliseconds()),
                                ),
                            )
                        }
                    },
                )
            } else {
                // Mode Surah: satu tombol ke Mushaf (halaman pertama surah ini).
                Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { vm.onEvent(ReaderEvent.OpenMushafForSurah(surah)) }) { Text("Mushaf") }
                }
            }
            // Tata cetakan eksak butuh ukuran tetap: slider hanya di mode Surah
            // (pengaturan permanen ada di Pengaturan).
            if (state.mode == ReadMode.SURAH) {
                Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Ukuran huruf", style = MaterialTheme.typography.bodySmall)
                    Text("${(state.fontScale * 100).toInt()}%", style = MaterialTheme.typography.bodySmall)
                }
                Slider(
                    value = state.fontScale, onValueChange = { vm.onEvent(ReaderEvent.SetFontScale(it)) },
                    valueRange = 0.8f..2f, modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            when {
                state.loading -> CircularProgressIndicator(Modifier.padding(24.dp))
                state.error != null -> Column(Modifier.padding(24.dp)) {
                    Text(state.error!!, color = MaterialTheme.colorScheme.error)
                    Button(onClick = { vm.onEvent(ReaderEvent.Retry) }) { Text("Coba lagi") }
                }
                state.mode == ReadMode.SURAH -> LazyColumn(Modifier.fillMaxSize()) {
                    items(state.ayahs, key = { it.key }) { ayah ->
                        MushafText("﴿${toArabicDigits(ayah.ayah)}﴾ ${ayah.textUthmani}", fontScale = state.fontScale)
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

/** Bilah navy ala mushaf: kembali, judul surah, info Page/Juz, bookmark. */
@Composable
private fun MushafTopBar(
    surahLatin: String,
    page: Int,
    juz: Int,
    bookmarked: Boolean,
    onBack: () -> Unit,
    onBookmark: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().background(Color(0xFF143A5A)).padding(horizontal = 4.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = onBack) {
            Text("‹", style = MaterialTheme.typography.titleLarge, color = Color.White)
        }
        Column(Modifier.weight(1f).padding(start = 4.dp)) {
            Text(
                if (surahLatin.isNotBlank()) "Surah $surahLatin" else "Mushaf",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
            Text(
                "Page $page, Juz $juz",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.8f),
            )
        }
        TextButton(onClick = onBookmark) {
            Text("🔖", color = if (bookmarked) Color(0xFFFFD54F) else Color.White.copy(alpha = 0.5f))
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
                lines = state.lines,
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
    val rightLines = if (pairStart == state.page) state.lines else state.nextLines
    val leftLines = if (pairStart == state.page) state.nextLines else state.lines
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
                lines = leftLines,
                ayahs = leftAyahs,
                fontScale = state.fontScale,
                playingKey = state.audioKey,
                onAyahTap = { onEvent(ReaderEvent.PlayAyah(it)) },
                modifier = Modifier.weight(1f).fillMaxSize(),
            )
            MushafPageView(
                pageNumber = pairStart,
                lines = rightLines,
                ayahs = rightAyahs,
                fontScale = state.fontScale,
                playingKey = state.audioKey,
                onAyahTap = { onEvent(ReaderEvent.PlayAyah(it)) },
                modifier = Modifier.weight(1f).fillMaxSize(),
            )
        }
    }
}
