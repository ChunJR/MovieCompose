package com.chun.moviecompose.presentation.search_movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chun.moviecompose.domain.model.Movie
import com.chun.moviecompose.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _searchedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchedMovies = _searchedMovies

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchAllMoviesUseCase(query).cachedIn(viewModelScope).collect {
                _searchedMovies.value = it
            }
        }
    }
}
