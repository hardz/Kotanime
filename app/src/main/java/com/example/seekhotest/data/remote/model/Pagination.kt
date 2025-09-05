package com.example.seekhotest.data.remote.model

data class Pagination(
    val current_page: Int? = 0,
    val has_next_page: Boolean? = false,
    val items: Items? = Items(),
    val last_visible_page: Int? = 0
)