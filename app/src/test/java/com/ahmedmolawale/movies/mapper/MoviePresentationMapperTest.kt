package com.ahmedmolawale.movies.mapper

import com.ahmedmolawale.movies.data.DummyData
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.model.MoviePresentation
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MoviePresentationMapperTest {
    private val moviePresentationMapper: MoviePresentationMapper = MoviePresentationMapper()

    @Test
    fun `check that mapToDomain returns correct data`() {
        val moviePresentation: MoviePresentation = DummyData.moviePresentation
        val movie: Movie = moviePresentationMapper.mapToDomain(moviePresentation)
        assertThat(moviePresentation.id).isEqualTo(movie.id)
        assertThat(moviePresentation.title).isEqualTo(movie.title)
        assertThat(moviePresentation.originalTitle).isEqualTo(movie.originalTitle)
        assertThat(moviePresentation.backdropPath).isEqualTo(movie.backdropPath)
        assertThat(moviePresentation.popularity.toDouble()).isEqualTo(movie.popularity)
        assertThat(moviePresentation.posterPath).isEqualTo(movie.posterPath)
        assertThat(moviePresentation.voteAverage.toDouble()).isEqualTo(movie.voteAverage)
        assertThat(moviePresentation.voteCount).isEqualTo(movie.voteCount)
        assertThat(false).isEqualTo(movie.adult)
        assertThat(moviePresentation.overview).isEqualTo(movie.overview)
        assertThat(moviePresentation.releaseDate).isEqualTo(movie.releaseDate)
    }

    @Test
    fun `check that mapToPresentation returns correct data`() {
        val movie: Movie = DummyData.movie
        val moviePresentation: MoviePresentation = moviePresentationMapper.mapToPresentation(movie)
        assertThat(movie.id).isEqualTo(moviePresentation.id)
        assertThat(movie.title).isEqualTo(moviePresentation.title)
        assertThat(movie.originalTitle).isEqualTo(moviePresentation.originalTitle)
        assertThat(movie.backdropPath).isEqualTo(moviePresentation.backdropPath)
        assertThat(movie.popularity.toString()).isEqualTo(moviePresentation.popularity)
        assertThat(movie.posterPath).isEqualTo(moviePresentation.posterPath)
        assertThat(movie.voteAverage.toString()).isEqualTo(moviePresentation.voteAverage)
        assertThat(movie.voteCount).isEqualTo(moviePresentation.voteCount)
        assertThat("No").isEqualTo(moviePresentation.adult)
        assertThat(movie.overview).isEqualTo(moviePresentation.overview)
        assertThat(movie.releaseDate).isEqualTo(moviePresentation.releaseDate)
    }
}
