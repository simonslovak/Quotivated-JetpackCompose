// StoreFavorite.kt
package com.utb.quotivated.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.utb.quotivated.data_classes.QuoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreFavorite(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserQuotes")
        val USER_QUOTES_KEY = stringPreferencesKey("user_quotes")
        val GENERATE_QUOTE_COUNT_KEY = intPreferencesKey("generate_quote_count")
        val GENERATE_IMAGE_COUNT_KEY = intPreferencesKey("generate_image_count")
    }

    private val preferencesFlow: Flow<Preferences> = context.dataStore.data

    private val gson = Gson()

    val getQuotes: Flow<List<QuoteData>> = preferencesFlow
        .map { preferences ->
            val jsonString = preferences[USER_QUOTES_KEY] ?: return@map emptyList()
            val typeToken = object : TypeToken<List<QuoteData>>() {}.type
            gson.fromJson(jsonString, typeToken) ?: emptyList()
        }

    val getGenerateQuoteCount: Flow<Int> = preferencesFlow
        .map { preferences ->
            preferences[GENERATE_QUOTE_COUNT_KEY] ?: 0
        }

    val getGenerateImageCount: Flow<Int> = preferencesFlow
        .map { preferences ->
            preferences[GENERATE_IMAGE_COUNT_KEY] ?: 0
        }

    suspend fun saveQuotes(quotes: List<QuoteData>) {
        context.dataStore.edit { preferences ->
            preferences[USER_QUOTES_KEY] = gson.toJson(quotes)
        }
    }

    suspend fun saveGenerateQuoteCount(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[GENERATE_QUOTE_COUNT_KEY] = count
        }
    }

    suspend fun saveGenerateImageCount(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[GENERATE_IMAGE_COUNT_KEY] = count
        }
    }

    suspend fun clearQuotes() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_QUOTES_KEY)
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
