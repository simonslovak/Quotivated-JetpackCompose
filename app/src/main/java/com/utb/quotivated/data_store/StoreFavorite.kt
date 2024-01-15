package com.utb.quotivated.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class QuoteData(val text: String, val author: String, val image: ByteArray)

class StoreFavorite(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserQuotes")
        val USER_QUOTES_KEY = stringPreferencesKey("user_quotes")
    }

    private val preferencesFlow: Flow<Preferences> = context.dataStore.data

    private val gson = Gson()

    val getQuotes: Flow<List<QuoteData>> = preferencesFlow
        .map { preferences ->
            val jsonString = preferences[USER_QUOTES_KEY] ?: return@map emptyList()
            val typeToken = object : TypeToken<List<QuoteData>>() {}.type
            gson.fromJson(jsonString, typeToken) ?: emptyList()
        }

    suspend fun saveQuotes(quotes: List<QuoteData>) {
        val jsonString = gson.toJson(quotes)
        context.dataStore.edit { preferences ->
            preferences[USER_QUOTES_KEY] = jsonString
        }
    }

    suspend fun clearQuotes() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
