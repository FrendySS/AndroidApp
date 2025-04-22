package com.example.animeviewer.domain.models

data class Anime(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val rating: Float = 0f
)