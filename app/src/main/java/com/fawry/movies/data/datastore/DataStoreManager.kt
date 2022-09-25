package com.fawry.movies.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fawry.movies.util.ext.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {
    val LAST_UPDATE = longPreferencesKey("lastUpdateTimeStamp")
    val lastUpdateFlow: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_UPDATE] ?: 0
        }

    suspend fun setLastUpdate(timestamp: Long) {
        context.dataStore.edit { settings ->
            settings[LAST_UPDATE] = timestamp
        }
    }
}