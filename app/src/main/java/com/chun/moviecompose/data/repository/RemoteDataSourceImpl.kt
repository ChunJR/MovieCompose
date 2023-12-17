package com.chun.moviecompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chun.moviecompose.data.remote.MovieApi
import com.chun.moviecompose.domain.repository.RemoteDataSource
import com.chun.moviecompose.navigation.Screen
import com.chun.moviecompose.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl (
    private val movieApi: MovieApi,
    ) : RemoteDataSource {

    override fun searchMovies(): Flow<PagingData<Screen.Movie>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi, borutoDatabase),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}