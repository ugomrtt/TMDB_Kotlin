package com.example.profiladaptatif

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.ActeurDao
import com.example.myapplication.FilmDao
import com.example.myapplication.SerieDao
import com.example.profiladaptatif.Converters

@Database(entities = [FilmEntity::class, SerieEntity::class, ActeurEntity::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun serieDao(): SerieDao
    abstract fun acteurDao(): ActeurDao
}