package com.example.profiladaptatif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profiladaptatif.ui.theme.ProfilAdaptatifTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel : MainViewModel by viewModels()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { Screen(windowSizeClass, navController) }
                composable("listeFilms") { Films(viewmodel, navController) }
                composable("series") { Films(viewmodel, navController) }
                composable("acteurs") { Films(viewmodel, navController) }
            }


            ProfilAdaptatifTheme {
                // A surface container using the 'background' color from the theme
            }
        }
    }
}
