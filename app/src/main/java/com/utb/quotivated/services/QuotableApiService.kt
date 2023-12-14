package com.utb.quotivated.services

import com.utb.quotivated.interfaces.QuotableApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuotableApiService {
    private const val BASE_URL = "https://api.quotable.io/"

    val api: QuotableApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(QuotableApi::class.java)
    }
}