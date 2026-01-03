package com.freshlybakedapps.kotanime.data.remote.api

import com.freshlybakedapps.kotanime.data.remote.model.AnimeResponse
import retrofit2.http.GET

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getAnimeList(): AnimeResponse
}