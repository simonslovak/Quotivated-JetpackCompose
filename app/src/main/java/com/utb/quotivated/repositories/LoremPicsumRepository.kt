package com.utb.quotivated.repositories

import android.util.Log
import com.utb.quotivated.data_classes.Photo
import com.utb.quotivated.services.LoremPicsumService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoremPicsumRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://picsum.photos/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(LoremPicsumService::class.java)

    fun loadImageData(onImageDataLoaded: (ByteArray) -> Unit) {
        val call = service.getFixedSizeImage()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val imageData = response.body()?.bytes()
                    if (imageData != null) {
                        onImageDataLoaded(imageData)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("onFailureError", "Response failed")
            }
        })
    }
}