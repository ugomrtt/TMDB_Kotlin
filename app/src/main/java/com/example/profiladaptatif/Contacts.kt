package com.example.profiladaptatif

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Contacts() {
    Column(){
        Row() {
            Image(
                painterResource(R.drawable.icon_email),
                contentDescription = "email",
                modifier = Modifier.size(30.dp)
            )
            Text(text = "ugo.mariotto@etu.iut-tlse3.fr")
        }
        Row() {
            Image(
                painterResource(R.drawable.icon_email),
                contentDescription = "email",
                modifier = Modifier.size(30.dp)
            )
            Text(text = "Ugo Mariotto")
        }
    }
}