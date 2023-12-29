package com.utb.quotivated.services

import com.utb.quotivated.data_classes.Quote
import retrofit2.http.GET
import retrofit2.Call

interface QuotableService {
    @GET("random?maxLength=150")
    fun getRandomQuote(): Call<Quote>
}
