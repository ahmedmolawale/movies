package com.ahmedmolawale.movies.remote.contract

import com.ahmedmolawale.movies.data.contract.remote.MovieDiscoverRemote
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.exception.Failure
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.remote.ApiService
import com.ahmedmolawale.movies.remote.mapper.MovieRemoteModelMapper
import javax.inject.Inject

class MovieDiscoverRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieRemoteModelMapper: MovieRemoteModelMapper
) : MovieDiscoverRemote {
    override suspend fun discoverMovies(): Result<List<MovieEntity>> {
        return try {
            val res = apiService.discoverMovies()
            when (res.isSuccessful) {
                true -> {
                    res.body()?.let {
                        Result.Success(movieRemoteModelMapper.mapFromModelList(it.results))
                    } ?: Result.Success(emptyList())
                }
                false -> {
                    Result.Error(Failure.ServerError)
                }
            }
        } catch (e: Throwable) {
            Result.Error(Failure.ServerError)
        }
    }
}