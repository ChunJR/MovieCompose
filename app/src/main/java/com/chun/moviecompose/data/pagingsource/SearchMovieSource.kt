package com.chun.moviecompose.data.pagingsource

import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chun.moviecompose.data.remote.MovieApi
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.util.Constants
import javax.inject.Inject

class SearchMovieSource @Inject constructor(
    private val movieApi: MovieApi,
    private val searchText: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: Constants.INITIAL_PAGE
            val response = movieApi.searchMovies(
                searchText = searchText,
                apiKey = Constants.API_KEY,
                page = page)
            response.body()?.results.let { movies ->
                if (!movies.isNullOrEmpty()) {
                    LoadResult.Page(
                        data = movies,
                        prevKey = if(page == Constants.INITIAL_PAGE) null else page.minus(1),
                        nextKey = if (endOfPaginationReached) null else page.plus(1)
                    )
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null,
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
