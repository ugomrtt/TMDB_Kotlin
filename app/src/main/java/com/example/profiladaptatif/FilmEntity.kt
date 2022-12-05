package com.example.profiladaptatif

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profiladaptatif.Movie

@Entity
data class FilmEntity(val fiche: Movie, @PrimaryKey val id: String)