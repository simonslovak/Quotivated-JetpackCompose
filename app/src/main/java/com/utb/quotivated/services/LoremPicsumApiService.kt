package com.utb.quotivated.services

import com.utb.quotivated.interfaces.LoremPicsumApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoremPicsumApiService {
    private const val BASE_URL = "https://picsum.photos/v2/"

    val api: LoremPicsumApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoremPicsumApi::class.java)
    }
}