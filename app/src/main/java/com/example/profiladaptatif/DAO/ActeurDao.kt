package com.example.profiladaptatif.com.example.profiladaptatif.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profiladaptatif.com.example.profiladaptatif.Entities.ActeurEntity

@Dao
interface ActeurDao {
    @Query("SELECT * FROM acteurentity")
    suspend fun getFavActeurs(): List<ActeurEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActeur(film: ActeurEntity)

    @Query("DELETE FROM acteurEntity WHERE id = :id")
    suspend fun deleteActeur(id: kotlin.Int)
}