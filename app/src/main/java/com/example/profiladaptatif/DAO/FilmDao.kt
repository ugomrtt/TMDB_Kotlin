package com.example.profiladaptatif.com.example.profiladaptatif.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.FilmEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentity")
    suspend fun getFavFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Query("DELETE FROM filmentity WHERE id = :id")
    suspend fun deleteFilm(id: kotlin.Int)
}