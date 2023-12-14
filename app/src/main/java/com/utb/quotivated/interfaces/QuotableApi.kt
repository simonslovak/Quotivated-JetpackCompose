package com.utb.quotivated.interfaces

import com.utb.quotivated.data_classes.Quote
import retrofit2.http.GET
interface QuotableApi {
    @GET("quotes")
    suspend fun getQuotes(): List<Quote>
}