package com.chun.moviecompose.presentation.movie

import com.chun.moviecompose.domain.model.Movie

sealed class MovieState {
    object Loading: MovieState()
    data class Success(val movies: List<Movie>): MovieState()
    data class Error(val errorMessage: String?): MovieState()
}