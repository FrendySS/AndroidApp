package com.example.animeviewer.data.remote.models

data class AnimeResponse(
    val data: List<AnimeDto>
)

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val images: Images,
    val score: Float? = 0f
)

data class Images(
    val jpg: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)