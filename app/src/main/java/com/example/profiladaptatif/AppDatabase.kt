package com.example.profiladaptatif

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.FilmDao
import com.example.profiladaptatif.Converters

@Database(entities = [FilmEntity::class, SerieEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}