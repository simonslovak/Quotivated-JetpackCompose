package com.utb.quotivated.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.utb.quotivated.data_classes.DummyQuote
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.data_store_room.entities.QuoteEntity
import com.utb.quotivated.repositories.JsonDummyRepository
import com.utb.quotivated.repositories.LoremPicsumRepository
import com.utb.quotivated.repositories.QuotableRepository

class AppViewModel : ViewModel() {

    private val jsonDummyRepostiory = JsonDummyRepository()
//    private val quoteRepository = QuotableRepository()
    private val photoRepository = LoremPicsumRepository()

    private val _dummy = mutableStateOf<DummyQuote?>(null)
    val dummy: State<DummyQuote?> = _dummy

//    private val _quote = mutableStateOf<Quote?>(null)
//    val quote: State<Quote?> = _quote

    private val _photo = mutableStateOf<ByteArray?>(null)
    val photo: State<ByteArray?> = _photo

    var isFavorite by mutableStateOf(false)
    var favoriteItems by mutableStateOf(mutableListOf<QuoteEntity>())

    init {
        _dummy.value = null
//        _quote.value = null
        _photo.value = null
    }

    fun setLoadedData(dummy: DummyQuote?, photo: ByteArray?) {
//    fun setLoadedData(dummy: DummyQuote?, quote: Quote?, photo: ByteArray?) {
        _dummy.value = dummy
//        _quote.value = quote
        _photo.value = photo
    }

    fun loadRandomDummyQuote() {
        jsonDummyRepostiory.loadRandomDummyQuote { result ->
            _dummy.value = result
        }
    }

//    fun loadRandomQuote() {
//        quoteRepository.loadRandomQuote { result ->
//            _quote.value = result
//        }
//    }

    fun loadImageData() {
        photoRepository.loadImageData { imageData ->
            _photo.value = imageData
        }
    }
}