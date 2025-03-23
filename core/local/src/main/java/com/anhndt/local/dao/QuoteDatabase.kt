package com.anhndt.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anhndt.local.model.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDAO
}