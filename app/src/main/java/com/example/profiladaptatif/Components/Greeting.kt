package com.example.profiladaptatif


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Greeting(name: String) {
    Text(text = "$name",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold)
}