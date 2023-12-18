package com.chun.moviecompose.presentation.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chun.moviecompose.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    navController: NavHostController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val movieList = viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemBarColor)
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    viewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    viewModel.searchMovies(it)
                },
                onCloseClicked = {
                    viewModel.updateSearchQuery("")
                },
            )
        },
        content = {
            MovieLazyColumn(
                state = movieList,
                navController = navController
            )
        },
    )
}