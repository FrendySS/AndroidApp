package com.example.animeviewer.domain.usecases

import com.example.animeviewer.domain.models.Anime
import com.example.animeviewer.domain.repositories.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<List<Anime>> {
        return repository.getAnimeList()
    }
}