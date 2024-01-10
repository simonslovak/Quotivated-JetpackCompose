package com.utb.quotivated.data_store_room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.utb.quotivated.data_store_room.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuote(quote: QuoteEntity)

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)

    @Query("SELECT * FROM QuoteEntity")
    suspend fun getAllQuotes(): List<QuoteEntity>
}