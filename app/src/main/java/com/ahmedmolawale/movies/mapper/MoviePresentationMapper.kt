package com.ahmedmolawale.movies.mapper

import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.mapper.base.PresentationMapper
import com.ahmedmolawale.movies.model.MoviePresentation
import javax.inject.Inject

class MoviePresentationMapper @Inject constructor() : PresentationMapper<Movie, MoviePresentation> {
    override fun mapToPresentation(domain: Movie): MoviePresentation {
        return MoviePresentation(
            id = domain.id,
            adult = if (domain.adult) "Yes" else "No",
            posterPath = domain.posterPath,
            backdropPath = domain.backdropPath,
            releaseDate = domain.releaseDate,
            title = domain.title,
            popularity = domain.popularity.toString(),
            originalTitle = domain.originalTitle,
            overview = domain.overview,
            voteAverage = domain.voteAverage.toString(),
            voteCount = domain.voteCount
        )
    }

    override fun mapToDomain(presentation: MoviePresentation): Movie {
        return Movie(
            id = presentation.id,
            adult = presentation.adult == "Yes",
            posterPath = presentation.posterPath,
            backdropPath = presentation.backdropPath,
            releaseDate = presentation.releaseDate,
            title = presentation.title,
            popularity = presentation.popularity.toDouble(),
            originalTitle = presentation.originalTitle,
            overview = presentation.overview,
            voteAverage = presentation.voteAverage.toDouble(),
            voteCount = presentation.voteCount
        )
    }
}
