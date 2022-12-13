package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
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

    val credits = viewModel.credits.collectAsState()
    viewModel.creditMovie(movieid)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarNom(movie.original_title, navController)

        },
        content = {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    Text("${movie.original_title}", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movie.backdrop_path}",
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item(span = { GridItemSpan(2) }) {
                    Row() {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.width(45.dp))
                        Column() {
                            Text("${movie.release_date}", fontStyle = FontStyle.Italic)
                            movie.genres.forEach {
                                Text("${it.name} &")
                            }
                        }
                    }
                }
                item(span = { GridItemSpan(2) }) {
                    Column() {
                        Text("Synopsis", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("${movie.overview}")
                        Text("Têtes d'affiche", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
                        items(credits.value.cast) { credit ->
                            Card(
                                modifier = Modifier
                                    .padding(6.dp)
                            ) {
                                Column() {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500/${credit.profile_path}",
                                        contentDescription = null
                                    )
                                    Text("${credit.name}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    Text("${credit.character}", fontSize = 13.sp, fontWeight = FontWeight.Light)
                                }
                            }
                        }
            }
        },
        bottomBar = {
            BottomBar(navController)
        }
    )
}