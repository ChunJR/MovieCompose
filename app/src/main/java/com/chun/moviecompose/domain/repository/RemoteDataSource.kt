package com.chun.moviecompose.domain.repository

import androidx.paging.PagingData
import com.chun.moviecompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getMovies(): Flow<PagingData<Movie>>
    fun searchMovies(searchText: String): Flow<PagingData<Movie>>
}
