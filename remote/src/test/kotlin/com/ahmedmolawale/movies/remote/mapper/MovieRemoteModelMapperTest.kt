package com.ahmedmolawale.movies.remote.mapper

import com.ahmedmolawale.movies.remote.DummyData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieRemoteModelMapperTest {
    private val movieRemoteModelMapper: MovieRemoteModelMapper = MovieRemoteModelMapper()

    @Test
    fun `check that mapFromModel returns correct data`() {
        val movieRemotemodel = DummyData.movieRemoteModel
        val movieEntity = movieRemoteModelMapper.mapFromModel(movieRemotemodel)
        assertThat(movieRemotemodel.id).isEqualTo(movieEntity.id)
        assertThat(movieRemotemodel.adult).isEqualTo(movieEntity.adult)
        assertThat(movieRemoteModelMapper.IMAGE_BASE_URL.plus(movieRemotemodel.backdrop_path)).isEqualTo(movieEntity.backdropPath)
        assertThat(movieRemoteModelMapper.IMAGE_BASE_URL.plus(movieRemotemodel.poster_path)).isEqualTo(movieEntity.posterPath)
        assertThat(movieRemotemodel.popularity).isEqualTo(movieEntity.popularity)
        assertThat(movieRemotemodel.original_title).isEqualTo(movieEntity.originalTitle)
        assertThat(movieRemotemodel.title).isEqualTo(movieEntity.title)
        assertThat(movieRemotemodel.release_date).isEqualTo(movieEntity.releaseDate)
        assertThat(movieRemotemodel.overview).isEqualTo(movieEntity.overview)
        assertThat(movieRemotemodel.vote_average).isEqualTo(movieEntity.voteAverage)
        assertThat(movieRemotemodel.vote_count).isEqualTo(movieEntity.voteCount)
    }
}
