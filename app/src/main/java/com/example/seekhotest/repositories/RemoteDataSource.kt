package com.example.seekhotest.repositories

import com.example.seekhotest.core.room.entity.AnimeEntity
import com.example.seekhotest.data.Data

interface RemoteDataSource {
    suspend fun getAnimeById(id: Int): AnimeEntity?
    suspend fun getAllAnime(): List<Data>
}
