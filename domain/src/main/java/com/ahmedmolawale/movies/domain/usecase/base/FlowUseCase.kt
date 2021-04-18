package com.ahmedmolawale.movies.domain.usecase.base

import com.ahmedmolawale.movies.domain.functional.Result
import kotlinx.coroutines.flow.Flow

/**
 * A use case in Clean Architecture represents an execution unit of asynchronous work.
 * A [FlowUseCase] exposes a cold stream of values implemented with Kotlin [Flow].
 *
 */
interface FlowUseCase<in Params, out Type> {
    operator fun invoke(params: Params? = null): Flow<Result<Type>>
}
