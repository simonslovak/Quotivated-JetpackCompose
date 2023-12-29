package com.utb.quotivated.repositories

import android.util.Log
import com.utb.quotivated.data_classes.Quote
import com.utb.quotivated.services.QuotableService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotableRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.quotable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(QuotableService::class.java)

    fun loadRandomQuote(onQuoteLoaded: (Quote) -> Unit) {
        val call = service.getRandomQuote()

        call.enqueue(object : Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                if (response.isSuccessful) {
                    val quote = response.body()
                    if (quote != null) {
                        onQuoteLoaded(quote)
                    }
                }
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                Log.d("onFailureError", "Response failed")
            }
        })
    }
}