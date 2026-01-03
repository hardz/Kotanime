package com.freshlybakedapps.kotanime.data.remote

import com.freshlybakedapps.kotanime.data.local.entity.AnimeEntity
import com.freshlybakedapps.kotanime.data.remote.model.Data

interface RemoteDataSource {
    suspend fun getAnimeById(id: Int): AnimeEntity?
    suspend fun getAllAnime(): List<Data>
}