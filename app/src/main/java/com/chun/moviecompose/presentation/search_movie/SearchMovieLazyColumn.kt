package com.chun.moviecompose.presentation.search_movie

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chun.moviecompose.R
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.presentation.common.EmptyScreen
import com.chun.moviecompose.presentation.common.ShimmerEffect
import com.chun.moviecompose.ui.theme.EXTRA_SMALL_PADDING
import com.chun.moviecompose.ui.theme.IMAGE_HEIGHT
import com.chun.moviecompose.ui.theme.PurpleGrey80

@ExperimentalFoundationApi
@Composable
fun MovieLazyColumn(
    movies: LazyPagingItems<Movie>,
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    val isValid = handleResult(movies, searchQuery)
    if (isValid) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier,
        ) {
            items(count = movies.itemCount, key = movies.itemKey { it.id }) { index ->
                movies[index]?.let {
                    CategoryItem(it)
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    movie: Movie,
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
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            AsyncImage(
                modifier = modifier.fillMaxWidth().height(IMAGE_HEIGHT),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = movie.poster_path)
                    .placeholder(drawableResId = R.drawable.baseline_fireplace_24)
                    .error(drawableResId = R.drawable.baseline_fireplace_24)
                    .build(),
                contentDescription = stringResource(id = R.string.movie_logo),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            Text(
                text = movie.title,
                fontSize = 14.sp,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
        }
    }
}

@Composable
fun handleResult(
    movies: LazyPagingItems<Movie>,
    searchQuery: String,
): Boolean {
    if (searchQuery.isEmpty()) {
        EmptyScreen("Please input search text")
        return false
    }

    movies.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.NotLoading && movies.itemCount == 0 -> {
                EmptyScreen("Please input search text")
                false
            }

            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null -> {
                EmptyScreen(error.toString())
                false
            }

            else -> true
        }
    }
}