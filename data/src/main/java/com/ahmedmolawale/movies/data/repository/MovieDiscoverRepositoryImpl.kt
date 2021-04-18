package com.ahmedmolawale.movies.data.repository

import com.ahmedmolawale.movies.data.contract.cache.RecentMovieDiscoverCache
import com.ahmedmolawale.movies.data.contract.remote.MovieDiscoverRemote
import com.ahmedmolawale.movies.data.mapper.MovieEntityMapper
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieDiscoverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDiscoverRepositoryImpl @Inject constructor(
    private val movieDiscoverRemote: MovieDiscoverRemote,
    private val recentMovieDiscoverCache: RecentMovieDiscoverCache,
    private val movieEntityMapper: MovieEntityMapper
) : MovieDiscoverRepository {

    override fun getRecentMovieDiscover(): Flow<Result<List<Movie>>> {
        return flow {
            when (val discoveredMovies = recentMovieDiscoverCache.getRecentDiscoveries()) {
                is Result.Success -> {
                    emit(Result.Success(movieEntityMapper.mapToDomainList(discoveredMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(discoveredMovies.failure))
                }
            }
        }
    }

    override fun discoverMovies(): Flow<Result<List<Movie>>> {
        return flow {
            when (val discoveredMovies = movieDiscoverRemote.discoverMovies()) {
                is Result.Success -> {
                    recentMovieDiscoverCache.saveMovieDiscoveries(discoveredMovies.data)
                    emit(Result.Success(movieEntityMapper.mapToDomainList(discoveredMovies.data)))
                }
                is Result.Error -> {
                    emit(Result.Error(discoveredMovies.failure))
                }
            }
        }
    }
}
