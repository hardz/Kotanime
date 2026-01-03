package com.freshlybakedapps.kotanime.core.room.mapper

import com.freshlybakedapps.kotanime.data.local.entity.AnimeEntity
import com.freshlybakedapps.kotanime.data.remote.model.Data
import com.freshlybakedapps.kotanime.domain.Anime


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

/*
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
    this.map { it.mapToAnimeModel() }*/
