package com.chun.moviecompose.data.remote

import com.chun.moviecompose.domain.model.MovieListResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovieList(@Header("accept") resultType: String,
                  @Header("Authorization") apiKey: String,
                  @Query("page") page: Int,
                  @Query("language") language: String,
    ): Response<MovieListResponse>

    @GET("search/movie")
    suspend fun searchMovies(@Header("Authorization") apiKey: String,
                             @Query("query") searchText: String,
                             @Query("page") page: Int = 1): Response<MovieListResponse>

}
