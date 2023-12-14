package com.utb.quotivated.data_classes

data class Photo(
    val _id: String,
    val tags: List<String>,
    val content: String,
    val author: String,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)
