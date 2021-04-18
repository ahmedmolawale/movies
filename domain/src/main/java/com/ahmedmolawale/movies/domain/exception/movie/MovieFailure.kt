package com.ahmedmolawale.movies.domain.exception.movie

import com.ahmedmolawale.movies.domain.exception.Failure

class MovieFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class NonExistentMovie : Failure.FeatureFailure()
}
