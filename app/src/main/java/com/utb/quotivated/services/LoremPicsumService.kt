package com.utb.quotivated.services

import com.utb.quotivated.data_classes.Photo
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.Call

interface LoremPicsumService {
    @GET("400")
    fun getFixedSizeImage(): Call<ResponseBody>
}