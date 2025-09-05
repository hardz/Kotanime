package com.example.seekhotest.data

data class AnimeResponse(
    val `data`: List<Data>? = listOf(),
    val pagination: Pagination? = Pagination()
)