package com.binarydev.quran.core.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quran.composeapp.generated.resources.Res
import quran.composeapp.generated.resources.amiri_quran
import quran.composeapp.generated.resources.uthman_taha
import org.jetbrains.compose.resources.Font

/** Font Utsmani Madinah: KFGQPC Uthman Taha Naskh (King Fahd Complex). */
val UthmaniFont
    @Composable
    get() = FontFamily(Font(Res.font.uthman_taha))

/**
 * Font tanda kecil: Amiri Quran — KHUSUS untuk U+06DF (nol kecil waw mati).
 * Di KFGQPC glifnya 0.61em (blob hitam), di Amiri 0.16em (cincin mungil benar).
 */
val MarkFont
    @Composable
    get() = FontFamily(Font(Res.font.amiri_quran))

/** Karakter yang digambar dengan font tanda (bukan font utama). */
private const val MARK_CHARS = "\u06DF"

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
 * Tambahkan teks Utsmani ke AnnotatedString.Builder dengan font tanda
 * untuk karakter khusus — hormati warna latar (sorotan audio).
 * Catatan: bukan @Composable agar bisa dipakai dalam remember/buildAnnotatedString;
 * teruskan [markFont] dari MarkFont.
 */
fun AnnotatedString.Builder.appendUthmani(text: String, bg: Color = Color.Transparent, markFont: FontFamily) {
    var i = 0
    for (j in text.indices) {
        if (text[j] in MARK_CHARS) {
            if (j > i) withStyle(SpanStyle(background = bg)) { append(text.substring(i, j)) }
            withStyle(SpanStyle(background = bg, fontFamily = markFont)) { append(text[j].toString()) }
            i = j + 1
        }
    }
    if (i < text.length) withStyle(SpanStyle(background = bg)) { append(text.substring(i)) }
}

/**
 * Teks Mushaf Utsmani: RTL, justify, font KFGQPC, ukuran adaptif.
 */
@Composable
fun MushafText(arab: String, fontScale: Float = 1f, modifier: Modifier = Modifier) {
    val markFont = MarkFont
    val annotated = remember(arab, markFont) {
        androidx.compose.ui.text.buildAnnotatedString { appendUthmani(arab, markFont = markFont) }
    }
    SelectionContainer {
        Text(
            text = annotated,
            style = uthmaniStyle(fontScale),
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
