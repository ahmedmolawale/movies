package com.ahmedmolawale.movies.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmolawale.movies.model.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    private val _movie = MutableLiveData<MoviePresentation>()
    val movie: LiveData<MoviePresentation>
        get() = _movie

    fun setMovieDetail(moviePresentation: MoviePresentation) {
        _movie.value = moviePresentation
    }
}
