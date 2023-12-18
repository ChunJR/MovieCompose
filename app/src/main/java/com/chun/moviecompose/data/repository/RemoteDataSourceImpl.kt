package com.chun.moviecompose.data.repository

import com.chun.moviecompose.data.remote.MovieApi
import com.chun.moviecompose.domain.repository.RemoteDataSource
import com.chun.moviecompose.presentation.movie.MovieState
import com.chun.moviecompose.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl (
    private val movieApi: MovieApi,
    ) : RemoteDataSource {

    override fun searchMovies(searchText: String, page: Int): Flow<MovieState> {
        return flow {
            emit(MovieState.Loading)
            try {
                val data = movieApi.searchMovies(apiKey = Constants.API_KEY, searchText = searchText, page = page)
                emit(MovieState.Success(data.Search))
            } catch (exception: Exception) {
                emit(MovieState.Error(exception.message))
            }
        }.flowOn(Dispatchers.IO)
    }
}