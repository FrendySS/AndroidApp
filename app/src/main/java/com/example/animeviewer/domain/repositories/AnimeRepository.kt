package com.example.animeviewer.domain.repositories

import com.example.animeviewer.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<List<Anime>>
    suspend fun refreshAnimeList()
    suspend fun getAnimeDetails(id: String): Anime
}