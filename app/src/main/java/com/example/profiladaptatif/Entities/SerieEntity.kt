package com.example.profiladaptatif.com.example.profiladaptatif.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profiladaptatif.Serie

@Entity
data class SerieEntity(val fiche: Serie, @PrimaryKey val id: String)