package com.binarydev.quran.core.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Teks Mushaf Utsmani: RTL, line-height lega, ukuran adaptif.
 * Font: pasang KFGQPC Hafs / Uthman Taha Naskh di composeResources/font
 * (unduh dari https://fonts.qurancomplex.gov.sa) lalu daftarkan di FontFamily.
 * Sementara memakai serif bawaan agar ringan.
 */
@Composable
fun MushafText(arab: String, fontScale: Float = 1f, modifier: Modifier = Modifier) {
    SelectionContainer {
        Text(
            text = arab,
            fontSize = (24 * fontScale).sp,
            lineHeight = (44 * fontScale).sp,
            textAlign = TextAlign.Right,
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
