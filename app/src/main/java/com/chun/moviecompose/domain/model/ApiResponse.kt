package com.chun.moviecompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val Response: String? = null,
    val totalResults: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val Search: List<Movie> = emptyList(),
)
