package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
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
fun Series(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    var recherche by rememberSaveable { mutableStateOf("") }

    if (series.isEmpty()) viewModel.affichSeries()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar({it -> viewModel.searchSeries(it)}, navController)

        },
        content = {

            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
                content = {
                    items(series) { serie ->
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
                                        model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                        contentDescription = null
                                    )
                                    IconButton(onClick = {
                                        if (serie.isFav) {
                                            viewModel.deleteFavSerie(serie)
                                        } else {
                                            viewModel.addFavSerie(serie)
                                        }
                                    }) {
                                        if (serie.isFav) {
                                            Image(
                                                painterResource(R.drawable.favfilled),
                                                contentDescription = "Fav",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .fillMaxSize()
                                            )
                                        }
                                        else{
                                            Image(
                                                painterResource(R.drawable.fav),
                                                contentDescription = "Fav",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .fillMaxSize()
                                            )
                                        }
                                    }
                                }
                                Text("${serie.name}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
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