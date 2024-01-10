package com.utb.quotivated.view_model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.data_store_room.AppDatabase
import com.utb.quotivated.data_store_room.entities.QuoteEntity
import com.utb.quotivated.repositories.LoremPicsumRepository
import com.utb.quotivated.repositories.QuotableRepository
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val quoteRepository = QuotableRepository()
    private val photoRepository = LoremPicsumRepository()

    private val _quote = mutableStateOf<Quote?>(null)
    val quote: State<Quote?> = _quote

    private val _photo = mutableStateOf<ByteArray?>(null)
    val photo: State<ByteArray?> = _photo

    var isFavorite by mutableStateOf(false)
    var favoriteItems by mutableStateOf(mutableListOf<QuoteEntity>())

    private val quoteDao = AppDatabase.getDatabase(application).quoteDao()

    init {
        _quote.value = null
        _photo.value = null
        loadFavoriteItems()
    }

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

    fun addToFavorites(id: Int, quote: String, author: String, imageBytes: ByteArray) {
        viewModelScope.launch {
            val quoteEntity = QuoteEntity(id, quote, author, imageBytes)
            favoriteItems.add(quoteEntity)
            quoteDao.insertQuote(quoteEntity)
        }
    }

    fun removeFromFavorites(item: QuoteEntity) {
        viewModelScope.launch {
            favoriteItems.remove(item)
            quoteDao.deleteQuote(item)
        }
    }

    private fun loadFavoriteItems() {
        viewModelScope.launch {
            favoriteItems = quoteDao.getAllQuotes().toMutableList()
        }
    }
}