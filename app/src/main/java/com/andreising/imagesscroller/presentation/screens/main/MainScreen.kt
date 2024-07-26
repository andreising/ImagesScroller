package com.andreising.imagesscroller.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreising.imagesscroller.presentation.navigation.Screen

@Composable
fun MainScreen(navHostController: NavHostController){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { navHostController.navigate(Screen.RemoteScreen.route) }) {
                Text(text = "Random picture")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navHostController.navigate(Screen.FavouritesScreen.route) }) {
                Text(text = "Favourite picture")
            }
        }
    }
}