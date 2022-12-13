package com.example.profiladaptatif

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.ActeurDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.FilmDao
import com.example.profiladaptatif.com.example.profiladaptatif.DAO.SerieDao
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.ActeurEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.FilmEntity
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.SerieEntity

@Database(entities = [FilmEntity::class, SerieEntity::class, ActeurEntity::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun serieDao(): SerieDao
    abstract fun acteurDao(): ActeurDao
}