package com.binarydev.quran.core.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.repository.BookmarkRepository
import com.binarydev.quran.db.QuranDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BookmarkRepositoryImpl(db: QuranDatabase) : BookmarkRepository {
    private val q = db.quranQueries

    override fun bookmarks(): Flow<List<Bookmark>> =
        q.allBookmarks().asFlow().mapToList(Dispatchers.IO).map { rows ->
            rows.map { Bookmark(it.key, it.surah.toInt(), it.ayah.toInt(), it.note, it.created_at) }
        }

    override suspend fun toggle(bookmark: Bookmark) {
        withContext(Dispatchers.IO) {
            if (q.bookmarkByKey(bookmark.key).executeAsOneOrNull() == null) {
                q.insertBookmark(
                    bookmark.key, bookmark.surah.toLong(), bookmark.ayah.toLong(),
                    bookmark.note, bookmark.createdAt,
                )
            } else {
                q.deleteBookmark(bookmark.key)
            }
        }
    }

    override fun lastRead(): Flow<Bookmark?> =
        q.getKv(LAST_READ).asFlow().mapToOneOrNull(Dispatchers.IO).map { v ->
            v?.split(":")?.mapNotNull { it.toIntOrNull() }
                ?.let { (s, a) -> Bookmark("$s:$a", s, a) }
        }

    override suspend fun setLastRead(bookmark: Bookmark) {
        withContext(Dispatchers.IO) {
            q.putKv(LAST_READ, "${bookmark.surah}:${bookmark.ayah}")
        }
    }

    companion object {
        const val LAST_READ = "last_read"
    }
}
