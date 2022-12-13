package com.example.profiladaptatif.com.example.profiladaptatif.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.profiladaptatif.Result

@Entity
data class ActeurEntity(val fiche: Result, @PrimaryKey val id: String)