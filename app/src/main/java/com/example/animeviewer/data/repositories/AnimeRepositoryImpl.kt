package com.example.animeviewer.data.repositories

import com.example.animeviewer.data.local.dao.AnimeDao
import com.example.animeviewer.data.mappers.toAnime
import com.example.animeviewer.data.mappers.toAnimeEntity
import com.example.animeviewer.data.remote.api.AnimeApiService
import com.example.animeviewer.domain.models.Anime
import com.example.animeviewer.domain.repositories.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: AnimeApiService,
    private val animeDao: AnimeDao
) : AnimeRepository {

    override fun getAnimeList(): Flow<List<Anime>> {
        return animeDao.getAllAnime().map { entities ->
            entities.map { it.toAnime() }
        }
    }

    override suspend fun refreshAnimeList() {
        try {
            val response = apiService.getAnimeList()
            val animeEntities = response.data.map { it.toAnimeEntity() }
            animeDao.insertAll(animeEntities)
        } catch (e: Exception) {
            // Обработка ошибок
        }
    }

    override suspend fun getAnimeDetails(id: String): Anime {
        // Сначала пытаемся получить из локальной БД
        val localAnime = animeDao.getAnimeById(id)
        if (localAnime != null) {
            return localAnime.toAnime()
        }

        // Если нет в БД, получаем из API
        val response = apiService.getAnimeDetails(id)
        val animeEntity = response.data.toAnimeEntity()
        animeDao.insertAll(listOf(animeEntity))
        return animeEntity.toAnime()
    }
}