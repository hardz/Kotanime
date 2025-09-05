package com.example.seekhotest.core.room.mapper

import com.example.seekhotest.core.room.entity.AnimeEntity
import com.example.seekhotest.data.Data
import com.example.seekhotest.data.model.Anime
import kotlin.collections.map



/*private fun AnimeEntity.toEntity(): AnimeEntity = AnimeEntity(
    id = id,
    title = title,
    rating = rating,
    score = score,
    trailerYoutubeId = trailerYoutubeId,
    imageUrl = imageUrl,
    genres = genres
)

fun List<AnimeEntity>.toAnimeEntityList(): List<AnimeEntity> = this.map { it.toEntity() }*/

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


// API -> Entity
fun Data.toEntity(): AnimeEntity {
    return AnimeEntity(
        id = mal_id,
        title = title,
        synopsis = synopsis,
        rating = score,
        trailerYoutubeId = trailer?.youtube_id,
        imageUrl = images?.jpg?.image_url,
        genres = genres?.map { it.name } as List<String>?,
        numberofEpisodes = episodes
    )
}

// Entity -> Domain
fun AnimeEntity.toDomain(): Anime {
    return Anime(
        id = id,
        title = title,
        rating = rating,
        trailerYoutubeId = trailerYoutubeId,
        imageUrl = imageUrl,
        genres = genres,
        synopsis = synopsis,
        numberofEpisodes = numberofEpisodes
    )
}



fun AnimeEntity.mapToAnimeModel(): Anime {
    return Anime(
        id = id,
        title = title,
        rating = rating,
        trailerYoutubeId = trailerYoutubeId,
        imageUrl = imageUrl,
        genres = genres,
        synopsis = synopsis,
        numberofEpisodes = numberofEpisodes
    )
}

fun List<AnimeEntity>.mapToAnimeList(): List<Anime> =
    this.map { it.mapToAnimeModel() }
