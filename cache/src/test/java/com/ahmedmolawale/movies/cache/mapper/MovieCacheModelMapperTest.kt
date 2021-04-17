package com.ahmedmolawale.movies.cache.mapper

import com.ahmedmolawale.movies.cache.entity.DummyData
import com.ahmedmolawale.movies.cache.entity.MovieCacheModel
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.google.common.truth.Truth
import org.junit.Test

class MovieCacheModelMapperTest {

    private val movieCacheModelMapper: MovieCacheModelMapper = MovieCacheModelMapper()

    @Test
    fun `check that mapToModel returns correct data`() {
        val movieEntity: MovieEntity = DummyData.movieEntity
        val movieCacheModel: MovieCacheModel = movieCacheModelMapper.mapToModel(movieEntity)
        Truth.assertThat(movieEntity.id).isEqualTo(movieCacheModel.id)
        Truth.assertThat(movieEntity.title).isEqualTo(movieCacheModel.title)
        Truth.assertThat(movieEntity.originalTitle).isEqualTo(movieCacheModel.originalTitle)
        Truth.assertThat(movieEntity.backdropPath).isEqualTo(movieCacheModel.backdropPath)
        Truth.assertThat(movieEntity.popularity).isEqualTo(movieCacheModel.popularity)
        Truth.assertThat(movieEntity.posterPath).isEqualTo(movieCacheModel.posterPath)
        Truth.assertThat(movieEntity.voteAverage).isEqualTo(movieCacheModel.voteAverage)
        Truth.assertThat(movieEntity.voteCount).isEqualTo(movieCacheModel.voteCount)
        Truth.assertThat(movieEntity.adult).isEqualTo(movieCacheModel.adult)
        Truth.assertThat(movieEntity.overview).isEqualTo(movieCacheModel.overview)
        Truth.assertThat(movieEntity.releaseDate).isEqualTo(movieCacheModel.releaseDate)
    }

    @Test
    fun `check that mapToEntity returns correct data`() {
        val movieCacheModel: MovieCacheModel = DummyData.movieCacheModel
        val movieEntity: MovieEntity = movieCacheModelMapper.mapToEntity(movieCacheModel)
        Truth.assertThat(movieCacheModel.id).isEqualTo(movieEntity.id)
        Truth.assertThat(movieCacheModel.title).isEqualTo(movieEntity.title)
        Truth.assertThat(movieCacheModel.originalTitle).isEqualTo(movieEntity.originalTitle)
        Truth.assertThat(movieCacheModel.backdropPath).isEqualTo(movieEntity.backdropPath)
        Truth.assertThat(movieCacheModel.popularity).isEqualTo(movieEntity.popularity)
        Truth.assertThat(movieCacheModel.posterPath).isEqualTo(movieEntity.posterPath)
        Truth.assertThat(movieCacheModel.voteAverage).isEqualTo(movieEntity.voteAverage)
        Truth.assertThat(movieCacheModel.voteCount).isEqualTo(movieEntity.voteCount)
        Truth.assertThat(movieCacheModel.adult).isEqualTo(movieEntity.adult)
        Truth.assertThat(movieCacheModel.overview).isEqualTo(movieEntity.overview)
        Truth.assertThat(movieCacheModel.releaseDate).isEqualTo(movieEntity.releaseDate)
    }
}