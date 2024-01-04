package com.utb.quotivated.data_classes

data class Photo(
    val id: ByteArray,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
)

