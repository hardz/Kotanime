package com.example.seekhotest.repositories

import com.example.seekhotest.core.room.mapper.toDomain
import com.example.seekhotest.core.room.mapper.toEntity
import com.example.seekhotest.data.model.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.collections.emptyList

class AnimeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AnimeRepository {

    override suspend fun getTopAnime(): Flow<List<Anime>> = flow {
        try {
            val apiResponse = remoteDataSource.getAllAnime()
            if (apiResponse.isNotEmpty()) {
                localDataSource.deleteAllAnime()
                localDataSource.insertAllAnime(apiResponse.map { it.toEntity() })
            }
        } catch (e: Exception) {
            emit(emptyList()) // network failed, fallback to cache
        }
        emitAll(localDataSource.getAllAnime().map { list -> list.map { it.toDomain() } })
    }.flowOn(Dispatchers.IO)

    override suspend fun getAnimeById(animeId: Int): Flow<Anime?> {
        return localDataSource.getAnimeById(animeId)
            .map { it?.toDomain() }
            .flowOn(Dispatchers.IO)
    }

}