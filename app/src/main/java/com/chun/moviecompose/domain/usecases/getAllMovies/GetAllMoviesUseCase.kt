package com.chun.moviecompose.domain.usecases.searchAllMovies

import androidx.paging.PagingData
import com.chun.moviecompose.data.repository.Repository
import com.chun.moviecompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetAllMoviesUseCase(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}
