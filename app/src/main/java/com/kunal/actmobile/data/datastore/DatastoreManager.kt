package com.kunal.actmobile.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(
    private val context: Context
) {

    companion object {
        private const val COUNTRY_STORE_NAME = "country_data_store"
        private val COUNTRY_NAME = stringPreferencesKey("country_name")
        private val COUNTRY_CODE = stringPreferencesKey("country_code")
    }

    private val Context.countryDataStore: DataStore<Preferences> by preferencesDataStore(name = COUNTRY_STORE_NAME)

    val countryName: Flow<String?>
        get() = context.countryDataStore.data.map { preferences ->
            preferences[COUNTRY_NAME]
        }

    suspend fun saveCountryName(countryName: String) {
        context.countryDataStore.edit { preferences ->
            preferences[COUNTRY_NAME] = countryName
        }
    }

    val countryCode: Flow<String?>
        get() = context.countryDataStore.data.map { preferences ->
            preferences[COUNTRY_CODE]
        }

    suspend fun saveCountryCode(countryCode: String) {
        context.countryDataStore.edit { preferences ->
            preferences[COUNTRY_CODE] = countryCode
        }
    }
}