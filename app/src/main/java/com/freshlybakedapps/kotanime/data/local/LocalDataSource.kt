package com.freshlybakedapps.kotanime.data.local

import com.freshlybakedapps.kotanime.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllAnime(): Flow<List<AnimeEntity>>
    fun getAnimeById(id: Int): Flow<AnimeEntity?>
    fun insertAllAnime(anime: List<AnimeEntity>)
    fun deleteAllAnime()
}