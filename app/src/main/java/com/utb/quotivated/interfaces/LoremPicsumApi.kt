package com.utb.quotivated.interfaces

import com.utb.quotivated.data_classes.Photo
import retrofit2.http.GET

interface LoremPicsumApi {
    @GET("list")
    suspend fun getPhotos(): List<Photo>
}