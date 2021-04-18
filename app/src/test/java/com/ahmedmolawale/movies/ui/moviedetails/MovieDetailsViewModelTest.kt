package com.ahmedmolawale.movies.ui.moviedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmolawale.movies.data.DummyData
import com.ahmedmolawale.movies.util.MainCoroutineRule
import com.ahmedmolawale.movies.util.getOrAwaitValueTest
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        movieDetailsViewModel = MovieDetailsViewModel()
    }

    @Test
    fun `check that setMovieDetail returns correct data`() {
        val moviePresentation = DummyData.moviePresentation
        movieDetailsViewModel.setMovieDetail(moviePresentation)
        val res = movieDetailsViewModel.movie.getOrAwaitValueTest()
        Truth.assertThat(res).isNotNull()
        Truth.assertThat(moviePresentation.id).isEqualTo(res.id)
        Truth.assertThat(moviePresentation.adult).isEqualTo(res.adult)
        Truth.assertThat(moviePresentation.backdropPath).isEqualTo(res.backdropPath)
        Truth.assertThat(moviePresentation.posterPath).isEqualTo(res.posterPath)
        Truth.assertThat(moviePresentation.title).isEqualTo(res.title)
        Truth.assertThat(moviePresentation.originalTitle).isEqualTo(res.originalTitle)
        Truth.assertThat(moviePresentation.overview).isEqualTo(res.overview)
        Truth.assertThat(moviePresentation.popularity).isEqualTo(res.popularity)
        Truth.assertThat(moviePresentation.releaseDate).isEqualTo(res.releaseDate)
        Truth.assertThat(moviePresentation.voteAverage).isEqualTo(res.voteAverage)
        Truth.assertThat(moviePresentation.voteCount).isEqualTo(res.voteCount)
    }
}
