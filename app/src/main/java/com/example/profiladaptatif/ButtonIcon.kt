package com.example.profiladaptatif

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonIcon(navController: NavController, nomButton:String, navName: String) {

    var src = "R.drawable.$nomButton".toInt()
    Button(onClick = { navController.navigate(navName) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(src),
                contentDescription = "$nomButton",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .fillMaxSize()
            )
            Text(text = "$nomButton")
        }
    }
}