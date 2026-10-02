package com.binarydev.quran.core.common

/** Result ringan tanpa exception berat — cocok untuk offline-first. */
sealed interface AppResult<out T> {
    data class Ok<T>(val data: T) : AppResult<T>
    data class Err(val message: String, val cause: Throwable? = null) : AppResult<Nothing>
    data object Loading : AppResult<Nothing>
}

inline fun <T, R> AppResult<T>.map(transform: (T) -> R): AppResult<R> = when (this) {
    is AppResult.Ok -> AppResult.Ok(transform(data))
    is AppResult.Err -> this
    AppResult.Loading -> AppResult.Loading
}
