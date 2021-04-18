package com.ahmedmolawale.movies.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieCacheModel(
    @PrimaryKey
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
    val voteCount: Long,

) {
    var isDiscovered: Boolean = false
}
