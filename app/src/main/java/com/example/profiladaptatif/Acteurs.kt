package com.example.profiladaptatif

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
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
fun Acteurs(viewModel: MainViewModel, navController: NavController) {
    val acteurs by viewModel.acteurs.collectAsState()

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    if (acteurs.isEmpty()) viewModel.affichActeurs()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar({it -> viewModel.searchMovies(it)})

        },
        content = {

            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp),
                content = {
                    items(acteurs) { acteur ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                        ) {
                            Column() {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                    contentDescription = null
                                )
                                Text("${acteur.name}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
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