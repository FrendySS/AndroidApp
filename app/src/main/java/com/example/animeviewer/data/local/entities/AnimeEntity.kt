package com.example.animeviewer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val rating: Float
)