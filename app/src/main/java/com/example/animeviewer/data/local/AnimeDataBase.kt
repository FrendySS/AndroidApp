package com.example.animeviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animeviewer.data.local.dao.AnimeDao
import com.example.animeviewer.data.local.entities.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}