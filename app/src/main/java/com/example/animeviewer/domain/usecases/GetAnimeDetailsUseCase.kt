package com.example.animeviewer.domain.usecases

import com.example.animeviewer.domain.models.Anime
import com.example.animeviewer.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(id: String): Anime {
        return repository.getAnimeDetails(id)
    }
}