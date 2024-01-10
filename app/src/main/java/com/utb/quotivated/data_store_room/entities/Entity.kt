package com.utb.quotivated.data_store_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "quote_text")val quoteText: String,
    @ColumnInfo(name = "quote_author")val quoteAuthor: String,
    @ColumnInfo(name = "image_bytes")val imageBytes: ByteArray
)