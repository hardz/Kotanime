package com.freshlybakedapps.kotanime.data.remote.model

data class Trailer(
    val embed_url: String? = "",
    val images: ImagesX? = ImagesX(),
    val url: String? = "",
    val youtube_id: String? = ""
)