package com.utb.quotivated.view_model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utb.quotivated.data_classes.DummyQuote
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.data_store.QuoteData
import com.utb.quotivated.data_store.StoreFavorite
import com.utb.quotivated.repositories.JsonDummyRepository
import com.utb.quotivated.repositories.LoremPicsumRepository
import com.utb.quotivated.repositories.QuotableRepository
import kotlinx.coroutines.flow.Flow


class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val quoteRepository = QuotableRepository()
    private val photoRepository = LoremPicsumRepository()

    private val _quote = mutableStateOf<Quote?>(null)
    val quote: State<Quote?> = _quote

    private val _photo = mutableStateOf<ByteArray?>(null)
    val photo: State<ByteArray?> = _photo

    private val dataStore: StoreFavorite = StoreFavorite(application)

    val savedQuotes: Flow<List<QuoteData>> = dataStore.getQuotes

    var isFavorite by mutableStateOf(false)

    fun setLoadedData(quote: Quote?, photo: ByteArray?) {
        _quote.value = quote
        _photo.value = photo
    }

    fun loadRandomQuote() {
        quoteRepository.loadRandomQuote { result ->
            _quote.value = result
        }
    }

    fun loadImageData() {
        photoRepository.loadImageData { imageData ->
            _photo.value = imageData
        }
    }

    suspend fun saveQuotes(quotes: List<QuoteData>) {
        dataStore.saveQuotes(quotes)
    }

    suspend fun clearQuotes() {
        dataStore.clearQuotes()
    }
}

