package com.example.profiladaptatif

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen(classes: WindowSizeClass, navController: NavController) {

    when (classes.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Home()
                        Spacer(modifier = Modifier.height(30.dp))
                        Contacts()
                        Button(onClick = { navController.navigate("listeFilms") }) {
                            Text(text = "Démarrer")
                        }
                    }
                }
            }
        }

        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Home()
                Contacts()
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Démarrer")
                }
            }
        }
    }
}