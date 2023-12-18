package com.chun.moviecompose.presentation.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun.moviecompose.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieState>(MovieState.Loading)
    val uiState: StateFlow<MovieState> = _uiState

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMovies(query: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            useCases.searchHeroUseCase(query).cachedIn(viewModelScope).collect {
//                _searchedHeroes.value = it
//            }
//        }
        viewModelScope.launch {
            useCases.searchAllMoviesUseCase(query, 1)
                .collect { state ->
                    _uiState.value = state
                }
        }
    }
}
