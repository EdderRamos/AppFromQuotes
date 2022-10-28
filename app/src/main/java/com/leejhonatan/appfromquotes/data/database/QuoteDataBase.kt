package com.leejhonatan.appfromquotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leejhonatan.appfromquotes.data.database.dao.QuoteDao
import com.leejhonatan.appfromquotes.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDataBase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}