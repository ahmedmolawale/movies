package com.ahmedmolawale.movies.fakes

import com.ahmedmolawale.movies.data.DummyData
import com.ahmedmolawale.movies.domain.exception.Failure
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieDiscoverRepository
import com.ahmedmolawale.movies.util.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMovieDiscoverRepository : MovieDiscoverRepository {

    private var moviesFlow: Flow<Result<List<Movie>>> = flowOf(Result.Success(DummyData.movies))

    var movieListResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            moviesFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<Result<List<Movie>>> {
        return when (type) {
            ResponseType.DATA -> moviesFlow
            ResponseType.EMPTY_DATA -> flowOf(Result.Success(listOf()))
            ResponseType.ERROR -> {
                flowOf(Result.Error(Failure.ServerError))
            }
        }
    }

    override fun getRecentMovieDiscover(): Flow<Result<List<Movie>>> {
        return moviesFlow
    }

    override fun discoverMovies(): Flow<Result<List<Movie>>> {
        return moviesFlow
    }
}
