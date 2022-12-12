package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profiladaptatif.FilmEntity
import com.example.profiladaptatif.SerieEntity

@Dao
interface SerieDao {
    @Query("SELECT * FROM serieentity")
    suspend fun getFavSeries(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(serie: SerieEntity)

    @Query("DELETE FROM serieentity WHERE id = :id")
    suspend fun deleteSerie(id: kotlin.Int)
}