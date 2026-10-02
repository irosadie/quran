package com.binarydev.quran.core.domain.usecase

import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.domain.model.MushafPage
import com.binarydev.quran.core.domain.repository.QuranRepository

/** Use-case tipis — ViewModel tetap ramping, logika baca di sini. */
class GetMushafPageUseCase(private val repo: QuranRepository) {
    suspend operator fun invoke(page: Int): AppResult<MushafPage> {
        val safe = page.coerceIn(1, 604)
        return repo.page(safe)
    }
}

class SearchAyahUseCase(private val repo: QuranRepository) {
    suspend operator fun invoke(query: String): AppResult<List<com.binarydev.quran.core.domain.model.Ayah>> {
        if (query.trim().length < 2) return AppResult.Ok(emptyList())
        return repo.search(query.trim())
    }
}
