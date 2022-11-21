package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailFilm(viewModel: MainViewModel, navController: NavController, movieid: String) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    val movie by viewModel.movie.collectAsState()

    viewModel.detailMovie(movieid)


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarNom(movie.original_title, navController)

        },
        content = {
            Column() {
                Text("${movie.original_title}", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${movie.backdrop_path}",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Row() {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                        contentDescription = null
                    )
                    Column() {
                        Text("${movie.release_date}")
                        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
                            content = {
                                items(movie.genres) { genre ->
                                    Text("${movie.genres}")
                                }
                            }
                        )
                    }
                }
            }
                  },
        bottomBar = {
            BottomBar(navController)
        }
    )
}