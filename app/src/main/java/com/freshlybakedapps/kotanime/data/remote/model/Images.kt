package com.freshlybakedapps.kotanime.data.remote.model

data class Images(
    val jpg: Jpg? = Jpg(),
    val webp: Webp? = Webp()
)