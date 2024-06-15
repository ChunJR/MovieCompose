package com.chun.moviecompose.presentation.search_movie

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.chun.moviecompose.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchMovieScreen(
    navController: NavHostController,
    viewModel: SearchMovieViewModel = hiltViewModel(),
) {
    val movieList = viewModel.searchedMovies.collectAsLazyPagingItems()
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
                    viewModel.searchMovies(it)
                },
                onSearchClicked = {
                    viewModel.searchMovies(it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                },
            )
        },
        content = {
            MovieLazyColumn(
                movies = movieList,
                searchQuery = searchQuery
            )
        },
    )
}