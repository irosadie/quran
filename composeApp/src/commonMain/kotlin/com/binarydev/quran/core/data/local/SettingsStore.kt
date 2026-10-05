package com.binarydev.quran.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.binarydev.quran.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

object SettingKeys {
    val ARAB_SCALE = floatPreferencesKey("arab_scale")
    val LATIN_SHOWN = booleanPreferencesKey("latin_shown")
}

/** DataStore Preferences di file platform (lihat dataFilePath). */
fun createSettingsStore(): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { dataFilePath("quran.preferences_pb").toPath() },
    )

class SettingsRepositoryImpl(private val store: DataStore<Preferences>) : SettingsRepository {
    override fun arabFontScale(): Flow<Float> =
        store.data.map { it[SettingKeys.ARAB_SCALE] ?: 1f }

    override suspend fun setArabFontScale(scale: Float) {
        store.edit { it[SettingKeys.ARAB_SCALE] = scale.coerceIn(0.8f, 2f) }
    }

    override fun latinShown(): Flow<Boolean> =
        store.data.map { it[SettingKeys.LATIN_SHOWN] ?: true }

    override suspend fun setLatinShown(shown: Boolean) {
        store.edit { it[SettingKeys.LATIN_SHOWN] = shown }
    }
}
