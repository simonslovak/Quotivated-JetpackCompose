package com.utb.quotivated.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.repositories.LoremPicsumRepository
import com.utb.quotivated.repositories.QuotableRepository

class AppViewModel : ViewModel() {
    private val quoteRepository = QuotableRepository()
    private val photoRepository = LoremPicsumRepository()

    private val _quote = mutableStateOf<Quote?>(null)
    val quote: State<Quote?> = _quote

    private val _photo = mutableStateOf<ByteArray?>(null)
    val photo: State<ByteArray?> = _photo

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        loadRandomQuote()
        loadImageData()
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
}