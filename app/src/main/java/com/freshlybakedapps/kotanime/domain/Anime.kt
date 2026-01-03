package com.freshlybakedapps.kotanime.domain

import androidx.room.PrimaryKey

data class Anime(
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