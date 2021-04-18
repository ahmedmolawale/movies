package com.ahmedmolawale.movies.domain.repository

import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDiscoverRepository {
    fun getRecentMovieDiscover(): Flow<Result<List<Movie>>>
    fun discoverMovies(): Flow<Result<List<Movie>>>
}
