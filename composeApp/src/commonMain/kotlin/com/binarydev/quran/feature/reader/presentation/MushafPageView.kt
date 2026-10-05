package com.binarydev.quran.feature.reader.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.binarydev.quran.core.data.local.SurahData
import com.binarydev.quran.core.designsystem.uthmaniStyle
import com.binarydev.quran.core.domain.model.Ayah

private val MushafCream = Color(0xFFFCF7E8)
private val MushafFrame = Color(0xFF8A6D3B)
private val PlayingHighlight = Color(0xFFFFE9A8)
private const val BASMALA = "بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ"

/**
 * Satu halaman mushaf: bingkai krem + teks mengalir kontinu (RTL justify)
 * + header surah & Basmalah + nomor halaman — mirip buku.
 * Tap ayat = putar audio dari ayat itu; ayat berbunyi disorot kuning.
 */
@Suppress("DEPRECATION") // ClickableText: API tap-per-offset paling ringan
@Composable
fun MushafPageView(
    pageNumber: Int,
    ayahs: List<Ayah>,
    fontScale: Float,
    playingKey: String?,
    onAyahTap: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .padding(8.dp)
            .background(MushafCream)
            .border(2.dp, MushafFrame)
            .padding(3.dp)
            .border(1.dp, MushafFrame.copy(alpha = 0.6f)),
    ) {
        if (ayahs.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Memuat halaman $pageNumber…", color = MushafFrame)
            }
        } else {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(10.dp)) {
                // Pecah per awal surah di tengah halaman (header + Basmalah)
                var start = 0
                for (i in ayahs.indices) {
                    val isNewSurah = i > 0 && ayahs[i].ayah == 1 && ayahs[i].surah != ayahs[i - 1].surah
                    if (isNewSurah) {
                        MushafParagraph(ayahs.subList(start, i), fontScale, playingKey, onAyahTap)
                        start = i
                    }
                }
                val first = ayahs[start]
                if (first.ayah == 1) SurahHeader(first.surah)
                MushafParagraph(ayahs.subList(start, ayahs.size), fontScale, playingKey, onAyahTap)
                Text(
                    text = "$pageNumber",
                    style = MaterialTheme.typography.labelSmall,
                    color = MushafFrame,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 6.dp),
                )
            }
        }
    }
}

/** Kepala surah ala mushaf + Basmalah (kecuali Al-Fatihah & At-Taubah). */
@Composable
private fun SurahHeader(surah: Int) {
    val name = SurahData.arabic.getOrElse(surah - 1) { "" }
    Box(
        Modifier.fillMaxWidth().padding(vertical = 6.dp)
            .border(1.dp, MushafFrame)
            .background(Color(0xFFF6ECD2))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text("سُورَةُ $name", style = uthmaniStyle(1.1f), color = Color(0xFF5D4A1F))
    }
    if (surah != 1 && surah != 9) {
        Text(
            BASMALA,
            style = uthmaniStyle(1f),
            color = Color(0xFF5D4A1F),
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
        )
    }
}

@Suppress("DEPRECATION")
@Composable
private fun MushafParagraph(
    ayahs: List<Ayah>,
    fontScale: Float,
    playingKey: String?,
    onAyahTap: (String) -> Unit,
) {
    if (ayahs.isEmpty()) return
    val text = buildAnnotatedString {
        for (a in ayahs) {
            pushStringAnnotation("ayah", a.key)
            val bg = if (a.key == playingKey) PlayingHighlight else Color.Transparent
            withStyle(SpanStyle(background = bg)) { append(a.textUthmani + " ") }
            append("﴿${a.ayah}﴾ ")
            pop()
        }
    }
    ClickableText(
        text = text,
        style = uthmaniStyle(fontScale).copy(color = Color(0xFF1A1A1A)),
        onClick = { offset ->
            text.getStringAnnotations("ayah", offset, offset).firstOrNull()?.let { onAyahTap(it.item) }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
