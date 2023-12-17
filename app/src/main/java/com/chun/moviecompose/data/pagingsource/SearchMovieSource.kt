package com.chun.moviecompose.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chun.moviecompose.data.remote.MovieApi
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.util.Constants
import javax.inject.Inject

class SearchMovieSource @Inject constructor(
    private val movieApi: MovieApi,
    private val searchText: String,
    private val page: Int,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val response = movieApi.searchMovies(apiKey = Constants.API_KEY, searchText = searchText, page = page)
            val movies = response.Search
            if (movies.isNotEmpty()) {
                LoadResult.Page(
                    data = movies,
                    prevKey = page,
                    nextKey = page + 1,
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null,
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
