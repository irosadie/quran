package com.binarydev.quran.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.binarydev.quran.db.QuranDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun createDbDriver(): SqlDriver =
    NativeSqliteDriver(QuranDatabase.Schema, "quran.db")

@OptIn(ExperimentalForeignApi::class)
actual fun dataFilePath(fileName: String): String {
    val dir = NSFileManager.defaultManager.URLForDirectory(
        NSDocumentDirectory, NSUserDomainMask, null, true, null,
    )!!.path!!
    return "$dir/$fileName"
}
