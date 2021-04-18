package com.ahmedmolawale.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePresentation(
    val id: Long,
    val adult: String,
    val backdropPath: String,
    val posterPath: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: Long
) : Parcelable
