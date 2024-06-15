package com.chun.moviecompose.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    var id: Int = -1,
    var title: String = "",
    var overview: String = "",
    var poster_path: String = "",
    var backdrop_path: String = "",
    var release_date: String = "",
    var vote_average: Float = -1f,
    var genres: List<Genres>? = null,
)

@Serializable
data class Genres(
    var id: Int = -1,
    var name: String = ""
)
