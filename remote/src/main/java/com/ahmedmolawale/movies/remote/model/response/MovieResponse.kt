package com.ahmedmolawale.movies.remote.model.response

import com.ahmedmolawale.movies.remote.model.MovieRemoteModel

data class MovieResponse(
    val page: Int,
    val results: List<MovieRemoteModel>
)
