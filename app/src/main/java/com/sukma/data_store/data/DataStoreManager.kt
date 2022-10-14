package com.sukma.data_store.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    suspend fun setUser(name: String, password: String) {
        context.loginDataStore.edit { preferences ->
            preferences[USERNAME_KEY] = name
            preferences[PASSWORD_KEY] = password
        }
    }

    suspend fun setUserLogin(isLogin: Boolean) {
        context.loginDataStore.edit { preferences ->
            preferences[LOGIN_STATUS_KEY] = isLogin
        }
    }

    fun getUser(): Flow<UserData> {
        return context.loginDataStore.data.map { preferences ->
            UserData(
                preferences[USERNAME_KEY] ?: "",
                preferences[PASSWORD_KEY] ?: "",
                preferences[LOGIN_STATUS_KEY] ?: false)
        }
    }

    fun getUserLogin(): Flow<Boolean> {
        return context.loginDataStore.data.map { preferences ->
            preferences[LOGIN_STATUS_KEY] ?: false
        }
    }

    companion object {
        private const val NAME_DATA_STORE = "login_preferences"
        private val USERNAME_KEY = stringPreferencesKey("name_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")
        private val LOGIN_STATUS_KEY = booleanPreferencesKey("login_status_key")


        val Context.loginDataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_DATA_STORE)

    }
}
