package com.binarydev.quran.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

private fun appDir(): File = File(System.getProperty("user.home"), ".quran").also { it.mkdirs() }

actual fun createDbDriver(): SqlDriver =
    JdbcSqliteDriver("jdbc:sqlite:${appDir().resolve("quran.db").absolutePath}")

actual fun dataFilePath(fileName: String): String =
    appDir().resolve(fileName).absolutePath
