// AppViewModel.kt
package com.utb.quotivated.view_model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.data_classes.QuoteData
import com.utb.quotivated.data_store.StoreFavorite
import com.utb.quotivated.repositories.LoremPicsumRepository
import com.utb.quotivated.repositories.QuotableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

// AppViewModel.kt
class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val quoteRepository = QuotableRepository()
    private val photoRepository = LoremPicsumRepository()

    private val _quote = mutableStateOf<Quote?>(null)
    val quote: State<Quote?> = _quote

    private val _photo = mutableStateOf<ByteArray?>(null)
    val photo: State<ByteArray?> = _photo

    private val _quoteData = mutableStateOf<QuoteData?>(null)
    val quoteData: State<QuoteData?> = _quoteData

    private val dataStore: StoreFavorite = StoreFavorite(application)

    val savedQuotes: Flow<List<QuoteData>> = dataStore.getQuotes

    // LiveData properties for generate quote and generate image counts
    private val _generateQuoteCount = dataStore.getGenerateQuoteCount
    val getQuoteCount: Flow<Int> get() = _generateQuoteCount

    private val _generateImageCount = dataStore.getGenerateImageCount
    val getImageCount: Flow<Int> get() = _generateImageCount

    var isFavorite by mutableStateOf(false)

    fun setLoadedData(quote: Quote?, photo: ByteArray?) {
        _quote.value = quote
        _photo.value = photo
    }

    fun setLoadedData(quoteData: QuoteData?) {
        _quoteData.value = quoteData
    }

    fun getQuoteData(): QuoteData? {
        return _quoteData.value
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

    suspend fun clearAll() {
        dataStore.clearAll()
    }

    suspend fun saveGenerateQuoteCount(count: Int) {
        dataStore.saveGenerateQuoteCount(count)
    }

    suspend fun saveGenerateImageCount(count: Int) {
        dataStore.saveGenerateImageCount(count)
    }
}

