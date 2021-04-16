package com.ahmedmolawale.movies.data.fakes

import com.ahmedmolawale.movies.data.contract.cache.RecentMovieSearchCache
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

class FakeRecentMovieSearchCache : RecentMovieSearchCache {

    private val cache = LinkedHashMap<Long, MovieEntity>()
    override suspend fun saveMovieSearch(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentSearch()
        movieEntities.forEach {
            cache[it.id] = it
        }
        return Result.Success(Unit)
    }

    override suspend fun getRecentSearch(): Result<List<MovieEntity>> {
        return Result.Success(cache.values.toList())
    }

    override suspend fun clearRecentSearch(): Result<Unit> {
        cache.clear()
        return Result.Success(Unit)
    }
}