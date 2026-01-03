package com.freshlybakedapps.kotanime.data.repositories

import com.freshlybakedapps.kotanime.domain.Anime
import kotlinx.coroutines.flow.Flow
import com.freshlybakedapps.kotanime.core.network.Result

interface AnimeRepository {
    suspend fun getTopAnime(): Flow<Result<List<Anime>>>
    suspend fun getAnimeById(animeId: Int): Flow<Result<Anime>>
}