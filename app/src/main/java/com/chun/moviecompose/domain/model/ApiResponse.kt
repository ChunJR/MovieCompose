package com.chun.moviecompose.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    var page: Int = -1,
    var total_pages: Int = -1,
    var total_results: Int = -1,
    var results: List<Movie> = emptyList(),
)
