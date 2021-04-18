package com.ahmedmolawale.movies.cache.mapper

import com.ahmedmolawale.movies.cache.entity.MovieCacheModel
import com.ahmedmolawale.movies.cache.mapper.base.CacheModelMapper
import com.ahmedmolawale.movies.data.model.MovieEntity
import javax.inject.Inject

class MovieCacheModelMapper @Inject constructor() : CacheModelMapper<MovieCacheModel, MovieEntity> {
    override fun mapToModel(entity: MovieEntity): MovieCacheModel {
        return MovieCacheModel(
            id = entity.id,
            adult = entity.adult,
            posterPath = entity.posterPath,
            backdropPath = entity.backdropPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            popularity = entity.popularity,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }

    override fun mapToEntity(model: MovieCacheModel): MovieEntity {
        return MovieEntity(
            id = model.id,
            adult = model.adult,
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            releaseDate = model.releaseDate,
            title = model.title,
            popularity = model.popularity,
            originalTitle = model.originalTitle,
            overview = model.overview,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }
}
