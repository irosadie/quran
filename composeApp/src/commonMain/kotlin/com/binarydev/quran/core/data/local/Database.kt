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
    // Tabel baru pasca-rilis v1 (DB lama tidak punya): pastikan ada.
    try {
        driver.execute(
            null,
            """
            CREATE TABLE IF NOT EXISTS mushaf_line (
              page INTEGER NOT NULL,
              line INTEGER NOT NULL,
              segs TEXT NOT NULL,
              PRIMARY KEY (page, line)
            )
            """.trimIndent(),
            0,
        )
    } catch (_: Exception) {
    }
    // Versioning format baris: cache lama dibangun ulang otomatis (bookmark aman).
    try {
        val cur = db.quranQueries.getKv(LINES_V).executeAsOneOrNull()
        if (cur != LAYOUT_VERSION) {
            db.quranQueries.deleteAllLines()
            db.quranQueries.putKv(LINES_V, LAYOUT_VERSION)
        }
    } catch (_: Exception) {
    }
    return db
}

private const val LINES_V = "lines_v"
private const val LAYOUT_VERSION = "4" // naikkan tiap format segmen berubah
