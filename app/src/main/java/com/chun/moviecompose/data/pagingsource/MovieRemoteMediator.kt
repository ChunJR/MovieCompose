//package com.chun.moviecompose.data.pagingsource
//
//import android.util.Log
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import com.chun.moviecompose.data.remote.MovieApi
//import com.chun.moviecompose.domain.model.Movie
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//import javax.inject.Inject
//
//@OptIn(ExperimentalPagingApi::class)
//class MovieRemoteMediator @Inject constructor(
//    private val movieApi: MovieApi,
//    private val movieDatabase: MovieDatabase,
//) : RemoteMediator<Int, Movie>() {
//    private val movieDao = movieDatabase.movieDao()
//    private val movieRemoteKeysDao = movieDatabase.movieRemoteKeysDao()
//
//    override suspend fun initialize(): InitializeAction {
//        val currentTime = System.currentTimeMillis()
//        val lastUpdated = movieRemoteKeysDao.getRemoteKeys(id = 1)?.lastUpdated ?: 0L
//        val cacheTimeout = 5
//        Log.d("RemoteMediator", "initialize: ${parseMillis(lastUpdated)}")
//
//        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
//        Log.d("RemoteMediator", "diff: $diffInMinutes")
//        return if (diffInMinutes.toInt() <= cacheTimeout) {
//            Log.d("RemoteMediator", "Up to date")
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            Log.d("RemoteMediator", "refresh")
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
//    }
//
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
//        return try {
//            val page = when (loadType) {
//                LoadType.REFRESH -> {
//                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                    remoteKeys?.nextPage?.minus(1) ?: 1
//                }
//
//                LoadType.PREPEND -> {
//                    val remoteKeys = getRemoteKeyForFirstItem(state)
//                    val prevPage = remoteKeys?.prevPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null,
//                        )
//                    prevPage
//                }
//
//                LoadType.APPEND -> {
//                    val remoteKeys = getRemoteKeyForTheLastItem(state)
//                    val nextPage = remoteKeys?.nextPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null,
//                        )
//                    nextPage
//                }
//            }
//
//            val response = movieApi.searchMovies(page = page)
//            if (response.moviees.isNotEmpty()) {
//                movieDatabase.withTransaction {
//                    if (loadType == LoadType.REFRESH) {
//                        movieDao.deleteAllMoviees()
//                        movieRemoteKeysDao.deleteAllRemoteKeys()
//                    }
//                    val prevPage = response.prevPage
//                    val nextPage = response.nextPage
//                    val keys = response.moviees.map { movie ->
//                        MovieRemoteKeys(
//                            id = movie.id,
//                            prevPage = prevPage,
//                            nextPage = nextPage,
//                            lastUpdated = response.lastUpdated,
//                        )
//                    }
//                    movieRemoteKeysDao.addRemoteKey(movieRemoteKeys = keys)
//                    movieDao.addMoviees(moviees = response.moviees)
//                }
//            }
//            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Movie>): MovieRemoteKeys? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                movieRemoteKeysDao.getRemoteKeys(id = id)
//            }
//        }
//    }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Movie>): MovieRemoteKeys? {
//        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
//            ?.let { movie ->
//                movieRemoteKeysDao.getRemoteKeys(id = movie.imdbID)
//            }
//    }
//
//    private suspend fun getRemoteKeyForTheLastItem(state: PagingState<Int, Movie>): MovieRemoteKeys? {
//        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
//            ?.let { movie ->
//                movieRemoteKeysDao.getRemoteKeys(id = movie.imdbID)
//            }
//    }
//
//    private fun parseMillis(millis: Long): String {
//        val date = Date(millis)
//        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
//        return format.format(date)
//    }
//}
