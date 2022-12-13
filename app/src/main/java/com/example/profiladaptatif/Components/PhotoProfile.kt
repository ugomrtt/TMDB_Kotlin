package com.example.profiladaptatif

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PhotoProfile() {
    Image(
        painterResource(R.drawable.pp),
        contentDescription = "Photo de profil",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(250.dp)
            .fillMaxSize()
            .clip(CircleShape)
            .border(
                BorderStroke(3.dp, Color.Black),
                CircleShape
            )
    )
}