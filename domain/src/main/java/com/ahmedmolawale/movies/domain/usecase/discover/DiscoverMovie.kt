package com.ahmedmolawale.movies.domain.usecase.discover

import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieDiscoverRepository
import com.ahmedmolawale.movies.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverMovie @Inject constructor(
    private val movieDiscoverRepository: MovieDiscoverRepository
) : FlowUseCase<Unit, List<Movie>> {
    override fun invoke(params: Unit?): Flow<Result<List<Movie>>> {
        return movieDiscoverRepository.discoverMovies()
    }
}
