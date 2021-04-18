package com.ahmedmolawale.movies.data.fakes

import com.ahmedmolawale.movies.data.contract.remote.MovieDiscoverRemote
import com.ahmedmolawale.movies.data.model.DummyData
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.data.util.ResponseType
import com.ahmedmolawale.movies.domain.exception.Failure
import com.ahmedmolawale.movies.domain.functional.Result

class FakeMovieDiscoverRemote : MovieDiscoverRemote {

    private var moviesResult: Result<List<MovieEntity>> =
        Result.Success(listOf(DummyData.movieEntity))
    var movieResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            moviesResult = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Result<List<MovieEntity>> {
        return when (type) {
            ResponseType.DATA -> moviesResult
            ResponseType.EMPTY_DATA -> Result.Success(emptyList())
            ResponseType.ERROR -> Result.Error(Failure.ServerError)
        }
    }

    override suspend fun discoverMovies(): Result<List<MovieEntity>> {
        return moviesResult
    }
}
