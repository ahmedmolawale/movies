package com.ahmedmolawale.movies.domain.usecase.search

import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieSearchRepository
import com.ahmedmolawale.movies.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentSearch @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository
) : FlowUseCase<Unit, List<Movie>> {
    override fun invoke(params: Unit?): Flow<Result<List<Movie>>> {
        return movieSearchRepository.getRecentMovieSearch()
    }
}
