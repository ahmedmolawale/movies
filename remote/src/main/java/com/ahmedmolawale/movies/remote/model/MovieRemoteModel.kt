package com.ahmedmolawale.movies.remote.model

data class MovieRemoteModel(
    val id: Long,
    val adult: Boolean,
    val backdrop_path: String,
    val poster_path: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Long
)
