package com.wenull.mathyourbrain.data.prefrences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {

    private val applicationContext = context.applicationContext

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val accessToken: Flow<String?> = applicationContext.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    suspend fun saveAccessToken(accessToken: String) {
        applicationContext.dataStore.edit { settings ->
            settings[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun clearData() {
        applicationContext.dataStore.edit {
            it.clear()
        }
    }

    val appInstalledJustNow: Flow<Boolean?> = applicationContext.dataStore.data
        .map { preferences ->
            preferences[APP_INSTALLED_JUST_NOW]
        }

    suspend fun appInstalledJustNow(appInstalledJustNow: Boolean) {

        applicationContext.dataStore.edit { settings ->
            settings[APP_INSTALLED_JUST_NOW] = appInstalledJustNow
        }
    }

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val APP_INSTALLED_JUST_NOW = booleanPreferencesKey("app_installed_now")
    }

}