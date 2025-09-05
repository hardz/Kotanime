package com.example.seekhotest.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.seekhotest.core.room.converters.Converters

@Entity
@TypeConverters(Converters::class)
data class AnimeEntity(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val synopsis: String?,
    val genres: List<String>?,
    val numberofEpisodes: Int?,
    val rating: Double?,
    val trailerYoutubeId: String?,
    val imageUrl: String?
)

/*
• Video Player to play the trailer if available (else show poster image)
• Title
• Plot/Synopsis
• Genre(s)
• Main Cast
• Number of Episodes
• Rating
• Poster Image
*/
