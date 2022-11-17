package com.example.profiladaptatif

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment


@Composable
fun Home() {
    Box(contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            PhotoProfile()
            Greeting(name = "Ugo Mariotto")
            Desc(job = "Etudiant / Alternant", entreprise = "Paul Sabatier")
        }
    }
}