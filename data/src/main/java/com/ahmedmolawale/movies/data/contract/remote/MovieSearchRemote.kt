package com.ahmedmolawale.movies.data.contract.remote

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface MovieSearchRemote {
    suspend fun searchMovies(movieName: String): Result<List<MovieEntity>>
}
