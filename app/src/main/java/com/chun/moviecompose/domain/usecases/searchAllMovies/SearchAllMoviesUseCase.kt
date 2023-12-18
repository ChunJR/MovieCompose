package com.chun.moviecompose.domain.usecases.searchAllMovies

import com.chun.moviecompose.data.repository.Repository
import com.chun.moviecompose.presentation.movie.MovieState
import kotlinx.coroutines.flow.Flow

class SearchAllMoviesUseCase(
    private val repository: Repository,
) {
    operator fun invoke(searchText: String, page: Int): Flow<MovieState> {
        return repository.searchMovies(searchText, page)
    }
}
