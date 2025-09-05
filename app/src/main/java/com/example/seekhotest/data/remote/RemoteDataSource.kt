package com.example.seekhotest.data.remote

import com.example.seekhotest.data.local.entity.AnimeEntity
import com.example.seekhotest.data.remote.model.Data

interface RemoteDataSource {
    suspend fun getAnimeById(id: Int): AnimeEntity?
    suspend fun getAllAnime(): List<Data>
}