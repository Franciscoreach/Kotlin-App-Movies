package io.devexpert.kmpmovies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.kmpmovies.data.Movie
import io.devexpert.kmpmovies.data.MoviesRepository
import io.devexpert.kmpmovies.data.RemoteMovie
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading = false,
                movies = moviesRepository.fetchPopularMovies()
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}