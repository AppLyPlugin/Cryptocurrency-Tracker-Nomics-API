package com.applyplugin.cryptocurrencytracker.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CONNECTIVITY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.FILTER_FILTER
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PREF_FILTER_FILTER_ID
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PREF_FILTER_FILTER_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PREF_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKey {
        val selectedFilter = stringPreferencesKey(PREF_FILTER_FILTER_KEY)
        val selectedFilterId = intPreferencesKey(PREF_FILTER_FILTER_ID)
        val connectivity = booleanPreferencesKey(CONNECTIVITY)
    }

    suspend fun saveFilter(filter: String, filterId: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKey.selectedFilter] = filter
            preferences[PreferenceKey.selectedFilterId] = filterId
        }
    }

    val readFilters: Flow<Filter> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedFilter = preferences[PreferenceKey.selectedFilter] ?: FILTER_FILTER
            val selectedFilterId = preferences[PreferenceKey.selectedFilterId] ?: 0
            Filter(
                selectedFilter,
                selectedFilterId
            )
        }


    suspend fun saveConnectivity(connectivity: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKey.connectivity] = connectivity

        }
    }

    val readConnectivity: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val connectivity = preferences[PreferenceKey.connectivity] ?: false
            connectivity
        }

}

data class Filter(
    val filter: String,
    val filterID: Int
)