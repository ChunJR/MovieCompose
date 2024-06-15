package com.chun.moviecompose.navigation

sealed class Screen(val route: String) {
    object Movie : Screen("movie_screen")
    object Search : Screen("search_screen")
}
