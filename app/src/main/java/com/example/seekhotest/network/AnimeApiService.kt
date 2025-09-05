package com.example.seekhotest.network

import com.example.seekhotest.data.AnimeResponse
import retrofit2.http.GET


interface AnimeApiService {
    @GET("top/anime")
    suspend fun getAnimeList(): AnimeResponse
}