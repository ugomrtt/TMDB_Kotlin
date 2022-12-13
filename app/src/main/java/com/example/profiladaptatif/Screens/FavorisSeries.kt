package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavorisSeries(viewModel: MainViewModel, navController: NavController) {
    val favSeries by viewModel.favSeries.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    if (favSeries.isEmpty()) viewModel.affichMovies()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar({ it -> viewModel.searchMovies(it) }, navController)

        },
        content = {

            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
                content = {
                    items(favSeries) { serie ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable { navController.navigate("detailSerie/${serie.id}") }
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500/${serie.fiche.poster_path}",
                                        contentDescription = null
                                    )
                                    IconButton(onClick = {
                                        viewModel.deleteFavSerie(serie.fiche)
                                    }) {
                                        Image(
                                            painterResource(R.drawable.favfilled),
                                            contentDescription = "Fav",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(25.dp)
                                                .fillMaxSize())
                                    }
                                }
                                Text(
                                    "${serie.fiche.name}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
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