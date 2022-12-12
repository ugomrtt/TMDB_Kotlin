package com.example.profiladaptatif

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profiladaptatif.Movie

@Entity
data class ActeurEntity(val fiche: Result, @PrimaryKey val id: String)