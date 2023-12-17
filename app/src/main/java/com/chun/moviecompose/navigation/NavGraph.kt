package com.chun.moviecompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chun.moviecompose.presentation.movie.MovieScreen

@Composable
fun SetUpNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Movie.route) {
            MovieScreen(navController = navController)
        }
    }
}
