package com.example.seekhotest.repositories

import com.example.seekhotest.data.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getTopAnime(): Flow<List<Anime>>
    suspend fun getAnimeById(animeId: Int): Flow<Anime?>
}