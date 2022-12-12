package com.example.profiladaptatif

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profiladaptatif.Movie

@Entity
data class SerieEntity(val fiche: Serie, @PrimaryKey val id: String)