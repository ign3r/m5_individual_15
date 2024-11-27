package com.example.m5individual15

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//private val Context.dataStore by preferencesDataStore("cards_data")
//
//object DataStoreManager {
//    private val CARDS_KEY = stringSetPreferencesKey("cards_list")
//
//    suspend fun saveCards(context: Context, cards: Set<String>) {
//        context.dataStore.edit { preferences ->
//            preferences[CARDS_KEY] = cards
//        }
//    }
//
//    fun getCards(context: Context): Flow<Set<String>> {
//        return context.dataStore.data.map { preferences ->
//            preferences[CARDS_KEY] ?: emptySet()
//        }
//    }
//}



import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey


//class DataStoreManager(private val context: Context) {
//
//    companion object {
//        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeBoarding")
//        val STORE_BOARD = booleanPreferencesKey("store_board")
//    }
//
//    val getBoarding: Flow<Boolean> = context.dataStore.data
//        .map { preference ->
//            preference[STORE_BOARD] ?: false
//        }
//
//    suspend fun saveBoarding(value: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[STORE_BOARD] = value
//        }
//    }
//}




val Context.dataStore by preferencesDataStore("user_preferences")

class DataStoreManager(private val context: Context) {
    private val SHOW_ONBOARDING_KEY = booleanPreferencesKey("show_onboarding")

    val shouldShowOnboarding: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[SHOW_ONBOARDING_KEY] ?: true }

    suspend fun setOnboardingShown() {
        context.dataStore.edit { preferences ->
            preferences[SHOW_ONBOARDING_KEY] = false
        }
    }
}
