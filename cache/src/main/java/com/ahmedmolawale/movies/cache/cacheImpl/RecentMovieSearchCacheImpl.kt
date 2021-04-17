package com.ahmedmolawale.movies.cache.cacheImpl

import com.ahmedmolawale.movies.cache.mapper.MovieCacheModelMapper
import com.ahmedmolawale.movies.cache.room.MoviesDao
import com.ahmedmolawale.movies.data.contract.cache.RecentMovieSearchCache
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result
import javax.inject.Inject

class RecentMovieSearchCacheImpl @Inject constructor(
    private val movieDao: MoviesDao,
    private val movieCacheModelMapper: MovieCacheModelMapper
) : RecentMovieSearchCache {
    override suspend fun saveMovieSearch(movieEntities: List<MovieEntity>): Result<Unit> {
        clearRecentSearch()
        val movieCacheModelList = movieCacheModelMapper.mapToModelList(movieEntities)
        movieCacheModelList.forEach {
            it.isDiscovered = false
        }
        movieDao.insertMovies(movieCacheModelList)
        return Result.Success(Unit)
    }

    override suspend fun getRecentSearch(): Result<List<MovieEntity>> {
        val movieCacheModelList = movieDao.fetchRecentSearch()
        return Result.Success(movieCacheModelMapper.mapToEntityList(movieCacheModelList))
    }


    override suspend fun clearRecentSearch(): Result<Unit> {
        movieDao.clearRecentSearch()
        return Result.Success(Unit)
    }
}