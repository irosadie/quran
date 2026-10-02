package com.binarydev.quran.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

/**
 * Helper responsif portabel (Android + iOS + Desktop) tanpa dependensi
 * window-core: klasifikasi dari lebar jendela aktual.
 * Compact <600dp (HP), Medium <1200dp (fold/tablet kecil), Expanded (tablet/desktop).
 */
enum class ScreenClass { COMPACT, MEDIUM, EXPANDED }

@Composable
fun rememberScreenClass(): ScreenClass {
    val size = LocalWindowInfo.current.containerSize
    val widthDp = with(LocalDensity.current) { size.width.toDp() }
    return when {
        widthDp >= 1200.dp -> ScreenClass.EXPANDED
        widthDp >= 600.dp -> ScreenClass.MEDIUM
        else -> ScreenClass.COMPACT
    }
}

@Composable
fun rememberIsLandscape(): Boolean {
    val size = LocalWindowInfo.current.containerSize
    return size.width > size.height
}
