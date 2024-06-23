package com.example.persistence

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

class DataStore(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val ALWAYS_ON_SCREEN_KEY = booleanPreferencesKey("always_on_screen")
        val WORKSHOP_KEY = booleanPreferencesKey("workshop")
    }

    private val _alwaysOnScreenFlow = MutableStateFlow(false)
    val alwaysOnScreenFlow: Flow<Boolean> get() = _alwaysOnScreenFlow

    private val _workshopFlow = MutableStateFlow(false)
    val workshopFlow: Flow<Boolean> get() = _workshopFlow

    init {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.data.map { preferences ->
                preferences[ALWAYS_ON_SCREEN_KEY] ?: false
            }.first { value ->
                _alwaysOnScreenFlow.value = value
                true
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.data.map { preferences ->
                preferences[WORKSHOP_KEY] ?: false
            }.first { value ->
                _workshopFlow.value = value
                true
            }
        }
    }

    suspend fun setAlwaysOnScreen(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[ALWAYS_ON_SCREEN_KEY] = enabled
        }
    }

    suspend fun setWorkshop(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[WORKSHOP_KEY] = enabled
        }
    }
}