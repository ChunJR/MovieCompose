package com.chun.moviecompose.data.remote

import com.chun.moviecompose.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET
    suspend fun searchMovies(@Query("apikey") apiKey: String,
                             @Query("s") searchText: String,
                             @Query("page") page: Int = 1): ApiResponse

}
