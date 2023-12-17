package com.chun.moviecompose.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chun.moviecompose.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        viewModelScope.launch {
//            useCases.sea()
//                .collect { state ->
//                    _uiState.value = state
//                }
        }
    }
}
