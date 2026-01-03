package com.freshlybakedapps.kotanime.data.remote.model

data class AnimeResponse(
    val `data`: List<Data>? = listOf(),
    val pagination: Pagination? = Pagination()
)