package com.example.seekhotest.data.remote.api

import com.example.seekhotest.data.remote.model.AnimeResponse
import retrofit2.http.GET

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getAnimeList(): AnimeResponse
}