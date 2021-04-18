package com.ahmedmolawale.movies.domain.repository

import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getRecentMovieSearch(): Flow<Result<List<Movie>>>
    fun searchMovies(param: String): Flow<Result<List<Movie>>>
}
