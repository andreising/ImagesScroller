package com.andreising.imagesscroller.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreising.imagesscroller.presentation.screens.favourites.FavouritesScreen
import com.andreising.imagesscroller.presentation.screens.main.MainScreen
import com.andreising.imagesscroller.presentation.screens.remote.RemoteScreen

@Composable
fun MainNavigationGraph() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController, startDestination = Screen.MainScreen.route,
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navHostController)
        }
        composable(Screen.RemoteScreen.route) {
            RemoteScreen()
        }
        composable(Screen.FavouritesScreen.route) {
            FavouritesScreen()
        }
    }
}