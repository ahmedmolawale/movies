package com.ahmedmolawale.movies.model.states

import com.ahmedmolawale.movies.model.MoviePresentation

data class MovieListView(
    val loading: Boolean = false,
    val errorMessage: Int? = null,
    val isEmpty: Boolean = false,
    val movies: List<MoviePresentation>? = null
)
