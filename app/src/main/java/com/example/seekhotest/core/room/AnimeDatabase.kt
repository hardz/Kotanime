package com.example.seekhotest.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.seekhotest.core.room.converters.Converters
import com.example.seekhotest.data.local.dao.AnimeDao
import com.example.seekhotest.data.local.entity.AnimeEntity


@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)  // Register the converter
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        const val DATABASE_NAME = "anime_db"
    }
}