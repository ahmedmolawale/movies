package com.ahmedmolawale.movies.data.contract.cache

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface RecentMovieSearchCache {
    suspend fun saveMovieSearch(movieEntities: List<MovieEntity>): Result<Unit>
    suspend fun getRecentSearch(): Result<List<MovieEntity>>
    suspend fun clearRecentSearch(): Result<Unit>
}