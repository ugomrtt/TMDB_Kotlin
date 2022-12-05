package com.example.profiladaptatif

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
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
fun BottomBar(navController: NavController){
    BottomAppBar() {
        Spacer(modifier = Modifier.width(40.dp))
        Button(onClick = { navController.navigate("listeFilms") }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(R.drawable.films),
                    contentDescription = "films",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .fillMaxSize()
                )
                Text(text = "Films")
            }
        }
        Spacer(modifier = Modifier.width(40.dp))
        Button(onClick = { navController.navigate("series") }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(R.drawable.series),
                    contentDescription = "series",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .fillMaxSize()
                )
                Text(text = "Series")
            }
        }
        Spacer(modifier = Modifier.width(40.dp))
        Button(onClick = { navController.navigate("acteurs") }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(R.drawable.acteurs),
                    contentDescription = "acteurs",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .fillMaxSize()
                )
                Text(text = "Acteurs")
            }
        }
    }
}