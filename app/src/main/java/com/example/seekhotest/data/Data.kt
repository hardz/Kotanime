package com.example.seekhotest.data

data class Data(
    val genres: List<Genre>? = listOf(),
    val images: Images? = Images(),
    val mal_id: Int? = 0,
//    val rating: String? = "",
    val season: String? = "",
    val synopsis: String? = "", //Plot/Synopsis
    val title: String? = "",
    val trailer: Trailer? = Trailer(),
    val url: String? = "",
    val episodes: Int? = 0,
    val score: Double? = 0.0 //rating
)