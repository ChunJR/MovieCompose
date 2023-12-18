package com.chun.moviecompose.data.repository

import com.chun.moviecompose.domain.repository.RemoteDataSource
import com.chun.moviecompose.presentation.movie.MovieState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun searchMovies(searchText: String, page: Int): Flow<MovieState> {
        return remoteDataSource.searchMovies(searchText, page)
    }
}
