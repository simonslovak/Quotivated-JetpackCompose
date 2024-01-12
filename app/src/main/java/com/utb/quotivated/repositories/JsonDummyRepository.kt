package com.utb.quotivated.repositories

import android.util.Log
import com.utb.quotivated.data_classes.DummyQuote
import com.utb.quotivated.services.JsonDummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonDummyRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/quotes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(JsonDummyService::class.java)

    fun loadRandomDummyQuote(onQuoteLoaded: (DummyQuote) -> Unit) {
        val call = service.getRandomDummyQuote()

        call.enqueue(object : Callback<DummyQuote> {
            override fun onResponse(call: Call<DummyQuote>, response: Response<DummyQuote>) {
                if (response.isSuccessful) {
                    val quote = response.body()
                    if (quote != null) {
                        onQuoteLoaded(quote)
                    }
                }
            }

            override fun onFailure(call: Call<DummyQuote>, t: Throwable) {
                Log.d("onFailureError", "Response failed")
            }
        }
        )
    }
}