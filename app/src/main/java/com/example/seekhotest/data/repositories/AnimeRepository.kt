package com.example.seekhotest.data.repositories

import com.example.seekhotest.domain.Anime
import kotlinx.coroutines.flow.Flow
import com.example.seekhotest.core.network.Result

interface AnimeRepository {
    suspend fun getTopAnime(): Flow<Result<List<Anime>>>
    suspend fun getAnimeById(animeId: Int): Flow<Result<Anime>>
}