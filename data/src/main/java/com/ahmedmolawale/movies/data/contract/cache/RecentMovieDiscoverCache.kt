package com.ahmedmolawale.movies.data.contract.cache

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface RecentMovieDiscoverCache {
    suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit>
    suspend fun getRecentDiscoveries(): Result<List<MovieEntity>>
    suspend fun clearRecentDiscoveries(): Result<Unit>
}