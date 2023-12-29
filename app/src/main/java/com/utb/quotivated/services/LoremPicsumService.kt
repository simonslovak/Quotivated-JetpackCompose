package com.utb.quotivated.services

import com.utb.quotivated.data_classes.Photo
import retrofit2.http.GET

interface LoremPicsumService {
    @GET("list")
    suspend fun getPhotos(): List<Photo>
}