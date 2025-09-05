package com.example.seekhotest.repositories

import com.example.seekhotest.core.room.entity.AnimeEntity
import com.example.seekhotest.data.model.Anime
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllAnime(): Flow<List<AnimeEntity>>
    fun getAnimeById(id: Int): Flow<AnimeEntity?>
    fun insertAllAnime(anime: List<AnimeEntity>)
    fun deleteAllAnime()
}
