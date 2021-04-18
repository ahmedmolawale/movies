package com.ahmedmolawale.movies.ui.movies.discover

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmolawale.movies.R
import com.ahmedmolawale.movies.data.DummyData
import com.ahmedmolawale.movies.domain.usecase.discover.DiscoverMovie
import com.ahmedmolawale.movies.domain.usecase.discover.GetRecentDiscoveries
import com.ahmedmolawale.movies.fakes.FakeMovieDiscoverRepository
import com.ahmedmolawale.movies.mapper.MoviePresentationMapper
import com.ahmedmolawale.movies.util.MainCoroutineRule
import com.ahmedmolawale.movies.util.ResponseType
import com.ahmedmolawale.movies.util.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDiscoverViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeMovieDiscoverRepository: FakeMovieDiscoverRepository
    private lateinit var movieDiscoverViewModel: MovieDiscoverViewModel
    private lateinit var moviePresentationMapper: MoviePresentationMapper

    @Before
    fun setup() {
        fakeMovieDiscoverRepository = FakeMovieDiscoverRepository()
        moviePresentationMapper = MoviePresentationMapper()
        movieDiscoverViewModel = MovieDiscoverViewModel(
            DiscoverMovie(fakeMovieDiscoverRepository),
            GetRecentDiscoveries(fakeMovieDiscoverRepository), moviePresentationMapper
        )
    }

    @Test
    fun `check that fetchRecentMoviesDiscoveries returns correct data`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.DATA
        movieDiscoverViewModel.fetchRecentMoviesDiscoveries()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNull()
        assertThat(res.isEmpty).isFalse()
        assertThat(res.movies)
            .isEqualTo(DummyData.movies.map(moviePresentationMapper::mapToPresentation))
    }

    @Test
    fun `check that fetchRecentMoviesDiscoveries returns empty data`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.EMPTY_DATA
        movieDiscoverViewModel.fetchRecentMoviesDiscoveries()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNull()
        assertThat(res.isEmpty).isTrue()
    }

    @Test
    fun `check that fetchRecentMoviesDiscoveries returns error`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.ERROR
        movieDiscoverViewModel.fetchRecentMoviesDiscoveries()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNotNull()
        assertThat(res.errorMessage).isNotEqualTo(R.string.movies_error)
    }

    @Test
    fun `check that discoverMovies returns correct data`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.DATA
        movieDiscoverViewModel.discoverMovies()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.isEmpty).isFalse()
        assertThat(res.movies)
            .isEqualTo(DummyData.movies.map(moviePresentationMapper::mapToPresentation))
    }

    @Test
    fun `check that discoverMovies returns empty data`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.EMPTY_DATA
        movieDiscoverViewModel.discoverMovies()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.isEmpty).isTrue()
    }

    @Test
    fun `check that discoverMovies returns error`() {
        fakeMovieDiscoverRepository.movieListResponseType = ResponseType.ERROR
        movieDiscoverViewModel.discoverMovies()
        val res = movieDiscoverViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNotNull()
        assertThat(res.errorMessage).isNotEqualTo(R.string.movies_error)
    }
}
