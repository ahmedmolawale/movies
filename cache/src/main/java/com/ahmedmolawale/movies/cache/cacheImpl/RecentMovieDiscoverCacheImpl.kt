package com.ahmedmolawale.movies.cache.cacheImpl

import com.ahmedmolawale.movies.cache.mapper.MovieCacheModelMapper
import com.ahmedmolawale.movies.cache.room.MoviesDao
import com.ahmedmolawale.movies.data.contract.cache.RecentMovieDiscoverCache
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result
import javax.inject.Inject

class RecentMovieDiscoverCacheImpl @Inject constructor(
    private val movieDao: MoviesDao,
    private val movieCacheModelMapper: MovieCacheModelMapper
) : RecentMovieDiscoverCache {
    override suspend fun saveMovieDiscoveries(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentDiscoveries()
        val movieCacheModelList = movieCacheModelMapper.mapToModelList(movieEntities)
        movieCacheModelList.forEach {
            it.isDiscovered = true
        }
        movieDao.insertMovies(movieCacheModelList)
        return Result.Success(Unit)
    }

    override suspend fun getRecentDiscoveries(): Result<List<MovieEntity>> {
        val movieCacheModelList = movieDao.fetchRecentDiscover()
        return Result.Success(movieCacheModelMapper.mapToEntityList(movieCacheModelList))
    }

    override suspend fun clearRecentDiscoveries(): Result<Unit> {
        movieDao.clearRecentDiscover()
        return Result.Success(Unit)
    }
}
