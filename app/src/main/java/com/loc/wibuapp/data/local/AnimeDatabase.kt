package com.loc.wibuapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loc.wibuapp.domain.model.Data

@Database(entities = [Data::class], version = 1)
@TypeConverters(AnimeTypeConverter::class)
abstract class AnimeDatabase: RoomDatabase() {

    abstract val animeDao: AnimeDao
}