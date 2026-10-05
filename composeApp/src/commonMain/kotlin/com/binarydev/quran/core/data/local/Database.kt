package com.binarydev.quran.core.data.local

import app.cash.sqldelight.db.SqlDriver
import com.binarydev.quran.db.QuranDatabase

/** expect/actual driver SQLite per platform (quran.db permanen). */
expect fun createDbDriver(): SqlDriver

/** Buka database; buat tabel bila pertama kali (idempoten). */
fun createDatabase(driver: SqlDriver = createDbDriver()): QuranDatabase {
    val db = QuranDatabase(driver)
    try {
        QuranDatabase.Schema.create(driver)
    } catch (_: Exception) {
        // Tabel sudah ada — lanjut.
    }
    return db
}
