package com.chun.moviecompose.presentation.movie

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
class MovieViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _movieList = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movieList = _movieList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getAllMoviesUseCase().cachedIn(viewModelScope).collect {
                _movieList.value = it
            }
        }
    }
}
