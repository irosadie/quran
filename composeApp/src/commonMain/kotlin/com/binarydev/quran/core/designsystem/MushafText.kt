package com.binarydev.quran.core.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quran.composeapp.generated.resources.Res
import quran.composeapp.generated.resources.amiri_quran
import org.jetbrains.compose.resources.Font

/** Font Utsmani Madinah: Amiri Quran (OFL) dari composeResources/font. */
val UthmaniFont
    @Composable
    get() = FontFamily(Font(Res.font.amiri_quran))

/** Gaya dasar teks Utsmani: RTL + spasi baris lega khas mushaf. */
@Composable
fun uthmaniStyle(fontScale: Float = 1f) = TextStyle(
    fontFamily = UthmaniFont,
    fontSize = (24 * fontScale).sp,
    lineHeight = (46 * fontScale).sp,
    textAlign = TextAlign.Justify,
    textDirection = TextDirection.Rtl,
)

/**
 * Teks Mushaf Utsmani: RTL, justify, font Amiri Quran, ukuran adaptif.
 */
@Composable
fun MushafText(arab: String, fontScale: Float = 1f, modifier: Modifier = Modifier) {
    SelectionContainer {
        Text(
            text = arab,
            style = uthmaniStyle(fontScale),
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
