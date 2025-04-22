package com.example.animeviewer.di

import com.example.animeviewer.domain.repositories.AnimeRepository
import com.example.animeviewer.domain.usecases.GetAnimeDetailsUseCase
import com.example.animeviewer.domain.usecases.GetAnimeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAnimeListUseCase(repository: AnimeRepository): GetAnimeListUseCase {
        return GetAnimeListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAnimeDetailsUseCase(repository: AnimeRepository): GetAnimeDetailsUseCase {
        return GetAnimeDetailsUseCase(repository)
    }
}