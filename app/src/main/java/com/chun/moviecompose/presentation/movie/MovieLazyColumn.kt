package com.chun.moviecompose.presentation.movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chun.moviecompose.R
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.presentation.common.EmptyScreen
import com.chun.moviecompose.presentation.common.ShimmerEffect
import com.chun.moviecompose.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun MovieLazyColumn(
    navController: NavHostController,
    state: State<MovieState>,
    modifier: Modifier = Modifier
) {
    val isValid = handleResult(state = state)
    if (isValid) {
        val movies = (state.value as MovieState.Success).movies
        LazyColumn(
            modifier = modifier
        ) {
            items(movies) { movie ->
                CategoryItem(movie, navController)
            }
        }
    }
}

@Composable
private fun CategoryItem(
    movie: Movie,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,
        backgroundColor = PurpleGrey80
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
                AsyncImage(
                    modifier = modifier
                        .weight(1.0f, true)
                        .height(100.dp)
                        .width(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = movie.Poster)
                        .placeholder(drawableResId = R.drawable.baseline_fireplace_24)
                        .error(drawableResId = R.drawable.baseline_fireplace_24)
                        .build(),
                    contentDescription = stringResource(id = R.string.movie_logo),
                    contentScale = ContentScale.Inside,
                )
            }
            Column(
                modifier = modifier
                    .weight(2.0f, true)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = movie.Title,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Composable
fun handleResult(
    state: State<MovieState>,
): Boolean {
    return when (state.value) {
        is MovieState.Success -> true
        is MovieState.Loading -> {
            ShimmerEffect()
            false
        }
        is MovieState.Error -> {
            val message = (state.value as MovieState.Error).errorMessage
            EmptyScreen(message)
            false
        }
    }
}