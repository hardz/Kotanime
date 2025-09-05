package com.example.seekhotest.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seekhotest.core.room.entity.AnimeEntity
import com.example.seekhotest.repositories.LocalDataSource
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao : LocalDataSource {

    @Query("SELECT * FROM AnimeEntity")
    override fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * from AnimeEntity where id = :id")
    override fun getAnimeById(id: Int): Flow<AnimeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAllAnime(anime: List<AnimeEntity>)

    @Query("DELETE FROM AnimeEntity")
    override fun deleteAllAnime()
}
