package com.utb.quotivated.repositories

import com.utb.quotivated.services.LoremPicsumService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoremPicsumRepository {
    private const val BASE_URL = "https://picsum.photos/v2/"

    val api: LoremPicsumService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoremPicsumService::class.java)
    }
}