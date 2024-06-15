package com.chun.moviecompose.presentation.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.chun.moviecompose.R
import com.chun.moviecompose.navigation.Screen
import com.chun.moviecompose.presentation.search_movie.MovieLazyColumn
import com.chun.moviecompose.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    navController: NavHostController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val movieList = viewModel.movieList.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemBarColor)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        color = MaterialTheme.colors.topAppBarContentColor,
                    )
                },
                backgroundColor = if (isSystemInDarkTheme()) Purple80 else Purple40,
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Search.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_icon),
                            tint = MaterialTheme.colors.topAppBarContentColor,
                        )
                    }
                },
            )
        },
        content = {
            MovieLazyColumn(
                movies = movieList,
            )
        },
    )
}