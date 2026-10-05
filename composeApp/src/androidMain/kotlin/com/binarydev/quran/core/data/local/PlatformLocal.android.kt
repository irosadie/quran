package com.binarydev.quran.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.binarydev.quran.db.QuranDatabase
import com.binarydev.quran.platform.AppContext

actual fun createDbDriver(): SqlDriver =
    AndroidSqliteDriver(QuranDatabase.Schema, AppContext.context, "quran.db")

actual fun dataFilePath(fileName: String): String =
    AppContext.context.filesDir.resolve(fileName).absolutePath
