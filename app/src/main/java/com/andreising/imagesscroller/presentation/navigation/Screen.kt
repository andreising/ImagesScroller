package com.andreising.imagesscroller.presentation.navigation

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main")
    data object RemoteScreen: Screen("remote")
    data object FavouritesScreen: Screen("favourites")
}