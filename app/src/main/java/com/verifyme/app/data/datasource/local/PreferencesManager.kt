package com.verifyme.app.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class PreferencesManager @Inject constructor(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

        // Keys
        const val TOKEN_KEY: String = "auth_token"

    }

    suspend fun saveString(key: String, value: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { prefs ->
            prefs[dataKey] = value
        }
    }

    fun getStringFlow(key: String): Flow<String?> {
        val dataKey = stringPreferencesKey(key)
        return context.dataStore.data.map { it[dataKey] }
    }

    suspend fun getStringOnce(key: String): String? {
        return getStringFlow(key).firstOrNull()
    }

    suspend fun clear(key: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { it.remove(dataKey) }
    }
}