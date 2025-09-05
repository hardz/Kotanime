package com.example.seekhotest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seekhotest.data.local.LocalDataSource
import com.example.seekhotest.data.local.entity.AnimeEntity
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
