package com.example.profiladaptatif

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun Desc(job: String, entreprise:String) {
    Text(text = "Je suis $job à $entreprise",
        fontSize = 18.sp)
}