package com.ahmedmolawale.movies.data.contract.remote

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface MovieDiscoverRemote {
    suspend fun discoverMovies(): Result<List<MovieEntity>>
}
