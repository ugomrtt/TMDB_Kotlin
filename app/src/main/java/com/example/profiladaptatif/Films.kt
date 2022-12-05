package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Films(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    var recherche by rememberSaveable { mutableStateOf("") }

    if (movies.isEmpty()) viewModel.affichMovies()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar({ it -> viewModel.searchMovies(it) })

        },
        content = {

            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
                content = {
                    items(movies) { movie ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable { navController.navigate("detailFilm/${movie.id}") }
                        ) {
                            Column() {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                    contentDescription = null
                                )
                                Text(
                                    "${movie.original_title}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("${movie.release_date}")
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController)
        }
    )
}