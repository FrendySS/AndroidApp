package com.example.animeviewer.data.mappers

import com.example.animeviewer.data.local.entities.AnimeEntity
import com.example.animeviewer.data.remote.models.AnimeDto
import com.example.animeviewer.domain.models.Anime

// Маппер из DTO в Entity
fun AnimeDto.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        id = mal_id.toString(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score ?: 0f
    )
}

// Маппер из Entity в доменную модель
fun AnimeEntity.toAnime(): Anime {
    return Anime(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        rating = rating
    )
}

// Маппер из DTO в доменную модель
fun AnimeDto.toAnime(): Anime {
    return Anime(
        id = mal_id.toString(),
        title = title,
        description = synopsis,
        imageUrl = images.jpg.image_url,
        rating = score ?: 0f
    )
}