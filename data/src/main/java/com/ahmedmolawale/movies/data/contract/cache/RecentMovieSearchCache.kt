package com.ahmedmolawale.movies.data.contract.cache

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

interface RecentMovieSearchCache {
    suspend fun saveMovieSearch(movieEntity: MovieEntity): Result<Unit>
    suspend fun getRecentSearch(): Result<List<MovieEntity>>
}