package com.example.seekhotest.data.repositories

import com.example.seekhotest.core.room.mapper.toDomain
import com.example.seekhotest.core.room.mapper.toEntity
import com.example.seekhotest.data.local.LocalDataSource
import com.example.seekhotest.data.remote.RemoteDataSource
import com.example.seekhotest.domain.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import com.example.seekhotest.core.network.Result

class AnimeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AnimeRepository {

    override suspend fun getTopAnime(): Flow<Result<List<Anime>>> = flow {
        emit(Result.Loading())  // start loading

        try {
            val apiResponse = remoteDataSource.getAllAnime()
            if (apiResponse.isNotEmpty()) {
                localDataSource.deleteAllAnime()
                localDataSource.insertAllAnime(apiResponse.map { it.toEntity() })
            }
        } catch (e: Exception) {
            emit(Result.Error("Network error: ${e.localizedMessage}"))
        }

        val cachedData = localDataSource.getAllAnime()
            .map { list -> list.map { it.toDomain() } }
            .firstOrNull().orEmpty()

        if (cachedData.isNotEmpty()) {
            emit(Result.Success(cachedData))
        } else {
            emit(Result.Error("No data available"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAnimeById(animeId: Int): Flow<Result<Anime>> = flow {
        emit(Result.Loading())

        try {
            val anime = localDataSource.getAnimeById(animeId).firstOrNull()
            if (anime != null) {
                emit(Result.Success(anime.toDomain()))
            } else {
                emit(Result.Error("Anime not found"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Database error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)
}