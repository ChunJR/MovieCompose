package com.chun.moviecompose.data.repository

import androidx.paging.PagingData
import com.chun.hypotheticalsport.presentation.match.MatchState
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun searchMovies(): Flow<PagingData<Movie>> {
        return remoteDataSource.searchMovies()
    }
}
