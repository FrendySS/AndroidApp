// data/remote/api/AnimeApiService.kt
package com.example.animeviewer.data.remote.api

import com.example.animeviewer.data.remote.models.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {
    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: String): AnimeDetailsResponse
}