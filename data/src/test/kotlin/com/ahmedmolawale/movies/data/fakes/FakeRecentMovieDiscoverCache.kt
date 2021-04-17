package com.ahmedmolawale.movies.data.fakes

import com.ahmedmolawale.movies.data.contract.cache.RecentMovieDiscoverCache
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result

class FakeRecentMovieDiscoverCache : RecentMovieDiscoverCache {

    private val cache = LinkedHashMap<Long, MovieEntity>()

    override suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentDiscoveries()
        movieEntities.forEach {
            cache[it.id] = it
        }
        return Result.Success(Unit)
    }

    override suspend fun getRecentDiscoveries(): Result<List<MovieEntity>> {
        return Result.Success(cache.values.toList())
    }

    override suspend fun clearRecentDiscoveries(): Result<Unit> {
        cache.clear()
        return Result.Success(Unit)
    }
}