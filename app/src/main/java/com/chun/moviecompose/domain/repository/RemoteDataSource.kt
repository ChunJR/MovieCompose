package com.chun.moviecompose.domain.repository

import com.chun.moviecompose.presentation.movie.MovieState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun searchMovies(searchText: String, page: Int): Flow<MovieState>
}
