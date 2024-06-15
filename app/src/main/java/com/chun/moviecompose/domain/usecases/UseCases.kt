package com.chun.moviecompose.domain.usecases

import com.chun.moviecompose.domain.usecases.searchAllMovies.GetAllMoviesUseCase
import com.chun.moviecompose.domain.usecases.searchAllMovies.SearchAllMoviesUseCase

data class UseCases(
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val searchAllMoviesUseCase: SearchAllMoviesUseCase,
)
