package com.ahmedmolawale.movies.data.mapper

import com.ahmedmolawale.movies.data.model.DummyData
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.model.Movie
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class MovieEntityMapperTest {
    private val movieEntityMapper: MovieEntityMapper = MovieEntityMapper()

    @Test
    fun `check that mapToDomain returns correct data`() {
        val movieEntity: MovieEntity = DummyData.movieEntity
        val movie: Movie = movieEntityMapper.mapToDomain(movieEntity)
        assertThat(movieEntity.id).isEqualTo(movie.id)
        assertThat(movieEntity.title).isEqualTo(movie.title)
        assertThat(movieEntity.originalTitle).isEqualTo(movie.originalTitle)
        assertThat(movieEntity.backdropPath).isEqualTo(movie.backdropPath)
        assertThat(movieEntity.popularity).isEqualTo(movie.popularity)
        assertThat(movieEntity.posterPath).isEqualTo(movie.posterPath)
        assertThat(movieEntity.voteAverage).isEqualTo(movie.voteAverage)
        assertThat(movieEntity.voteCount).isEqualTo(movie.voteCount)
        assertThat(movieEntity.adult).isEqualTo(movie.adult)
        assertThat(movieEntity.overview).isEqualTo(movie.overview)
        assertThat(movieEntity.releaseDate).isEqualTo(movie.releaseDate)
    }

    @Test
    fun `check that mapToEntity returns correct data`() {
        val movie: Movie = DummyData.movie
        val movieEntity: MovieEntity = movieEntityMapper.mapToEntity(movie)
        assertThat(movie.id).isEqualTo(movieEntity.id)
        assertThat(movie.title).isEqualTo(movieEntity.title)
        assertThat(movie.originalTitle).isEqualTo(movieEntity.originalTitle)
        assertThat(movie.backdropPath).isEqualTo(movieEntity.backdropPath)
        assertThat(movie.popularity).isEqualTo(movieEntity.popularity)
        assertThat(movie.posterPath).isEqualTo(movieEntity.posterPath)
        assertThat(movie.voteAverage).isEqualTo(movieEntity.voteAverage)
        assertThat(movie.voteCount).isEqualTo(movieEntity.voteCount)
        assertThat(movie.adult).isEqualTo(movieEntity.adult)
        assertThat(movie.overview).isEqualTo(movieEntity.overview)
        assertThat(movie.releaseDate).isEqualTo(movieEntity.releaseDate)
    }
}