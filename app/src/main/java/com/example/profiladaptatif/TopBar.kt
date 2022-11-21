package com.example.profiladaptatif

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBar(onSearch: (t:String) -> Unit){

    var recherche:String by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(true) }

    val keyboardController = LocalSoftwareKeyboardController.current

    if(state){
    TopAppBar(title = { Text("Fav'app") },
        actions = {
            IconButton(onClick = { state = !state }) {
                Image(
                    painterResource(R.drawable.search),
                    contentDescription = "Search",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .fillMaxSize()
                )
            }
        }
    )}

    else {
        TopAppBar(title = {
            IconButton(onClick = { state = !state }) {
                Image(
                    painterResource(R.drawable.left),
                    contentDescription = "Left",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .fillMaxSize()
                )
            }
            TextField(value = recherche,
                onValueChange = { newText ->
                    recherche = newText },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    recherche = recherche
                    keyboardController?.hide()
                    onSearch(recherche)
                })
            )
            }
        )
    }
}

@Composable
fun TopBarNom(nom: String, navController: NavController){

    TopAppBar(title = {
        IconButton(onClick = { navController.navigateUp() }) {
            Image(
                painterResource(R.drawable.left),
                contentDescription = "Left",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .fillMaxSize()
            )
        }
            Text(nom)
}
    )}
