package com.chun.moviecompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chun.moviecompose.data.pagingsource.GetMovieSource
import com.chun.moviecompose.data.pagingsource.SearchMovieSource
import com.chun.moviecompose.data.remote.MovieApi
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.domain.repository.RemoteDataSource
import com.chun.moviecompose.util.Constants
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl (
    private val movieApi: MovieApi,
    ) : RemoteDataSource {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEM_PER_PAGE),
            pagingSourceFactory = {
                GetMovieSource(movieApi = movieApi)
            },
        ).flow
    }

    override fun searchMovies(searchText: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchMovieSource(movieApi = movieApi, searchText = searchText)
            },
        ).flow
    }
}