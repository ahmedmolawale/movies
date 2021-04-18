package com.ahmedmolawale.movies.data.model

data class MovieEntity(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Long
)
