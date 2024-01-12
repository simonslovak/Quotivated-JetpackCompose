package com.utb.quotivated.services

import com.utb.quotivated.data_classes.DummyQuote
import retrofit2.Call
import retrofit2.http.GET

interface JsonDummyService {
    @GET("random")
    fun getRandomDummyQuote(): Call<DummyQuote>
}