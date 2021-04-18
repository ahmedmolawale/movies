package com.ahmedmolawale.movies.domain.usecase.search

import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieSearchRepository
import com.ahmedmolawale.movies.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovie @Inject constructor(
    private val movieSearchRepository: MovieSearchRepository
) : FlowUseCase<String, List<Movie>> {
    override fun invoke(params: String?): Flow<Result<List<Movie>>> {
        return movieSearchRepository.searchMovies(params!!)
    }
}
