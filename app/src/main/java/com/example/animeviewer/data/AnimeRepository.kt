package com.example.animeviewer.data

import com.example.animeviewer.domain.models.AnimeModel

object AnimeRepository {
    fun getAnimeList(): List<AnimeModel> {
        return listOf(
            AnimeModel("1", "Naruto", "Описание Наруто"),
            AnimeModel("2", "One Piece", "Описание One Piece"),
            AnimeModel("3", "Attack on Titan", "Описание AOT"),
            AnimeModel("4", "Death Note", "Описание Death Note")
        )
    }
}
