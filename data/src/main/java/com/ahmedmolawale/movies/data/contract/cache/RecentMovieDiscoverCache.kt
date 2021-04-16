package com.ahmedmolawale.movies.data.contract.cache

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface RecentMovieDiscoverCache {
    suspend fun saveMovieDiscover(movieEntity: MovieEntity): Result<Unit>
    suspend fun getRecentDiscover(): Result<List<MovieEntity>>
}