package com.ahmedmolawale.movies.remote.mapper

import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.remote.mapper.base.RemoteModelMapper
import com.ahmedmolawale.movies.remote.model.MovieRemoteModel

class MovieRemoteModelMapper : RemoteModelMapper<MovieRemoteModel, MovieEntity> {
    val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"
    override fun mapFromModel(model: MovieRemoteModel): MovieEntity {
        return MovieEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = IMAGE_BASE_URL.plus(model.backdrop_path),
            posterPath = IMAGE_BASE_URL.plus(model.poster_path),
            popularity = model.popularity,
            originalTitle = model.original_title,
            title = model.title,
            overview = model.overview,
            releaseDate = model.release_date,
            voteAverage = model.vote_average,
            voteCount = model.vote_count
        )
    }
}