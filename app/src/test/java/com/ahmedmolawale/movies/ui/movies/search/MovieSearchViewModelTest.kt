package com.ahmedmolawale.movies.ui.movies.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmolawale.movies.R
import com.ahmedmolawale.movies.data.DummyData
import com.ahmedmolawale.movies.domain.usecase.search.GetRecentSearch
import com.ahmedmolawale.movies.domain.usecase.search.SearchMovie
import com.ahmedmolawale.movies.fakes.FakeMovieSearchRepository
import com.ahmedmolawale.movies.mapper.MoviePresentationMapper
import com.ahmedmolawale.movies.util.MainCoroutineRule
import com.ahmedmolawale.movies.util.ResponseType
import com.ahmedmolawale.movies.util.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieSearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeMovieSearchRepository: FakeMovieSearchRepository
    private lateinit var movieSearchViewModel: MovieSearchViewModel
    private lateinit var moviePresentationMapper: MoviePresentationMapper

    @Before
    fun setup() {
        fakeMovieSearchRepository = FakeMovieSearchRepository()
        moviePresentationMapper = MoviePresentationMapper()
        movieSearchViewModel = MovieSearchViewModel(
            SearchMovie(fakeMovieSearchRepository),
            GetRecentSearch(fakeMovieSearchRepository), moviePresentationMapper
        )
    }

    @Test
    fun `check that fetchRecentMovies returns correct data`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.DATA
        movieSearchViewModel.fetchRecentMovies()
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNull()
        assertThat(res.isEmpty).isFalse()
        assertThat(res.movies).isEqualTo(DummyData.movies.map(moviePresentationMapper::mapToPresentation))
    }

    @Test
    fun `check that fetchRecentMovies returns empty data`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.EMPTY_DATA
        movieSearchViewModel.fetchRecentMovies()
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNull()
        assertThat(res.isEmpty).isTrue()
    }

    @Test
    fun `check that fetchRecentMovies returns error`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.ERROR
        movieSearchViewModel.fetchRecentMovies()
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNotNull()
        assertThat(res.errorMessage).isNotEqualTo(R.string.movies_error)
    }

    @Test
    fun `check that searchMovies returns correct data`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.DATA
        movieSearchViewModel.searchMovies(DummyData.SEARCH_STRING)
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.isEmpty).isFalse()
        assertThat(res.movies).isEqualTo(DummyData.movies.map(moviePresentationMapper::mapToPresentation))
    }

    @Test
    fun `check that searchMovies returns empty data`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.EMPTY_DATA
        movieSearchViewModel.searchMovies(DummyData.SEARCH_STRING)
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.isEmpty).isTrue()
    }

    @Test
    fun `check that searchMovies returns error`() {
        fakeMovieSearchRepository.movieListResponseType = ResponseType.ERROR
        movieSearchViewModel.searchMovies(DummyData.SEARCH_STRING)
        val res = movieSearchViewModel.movieListView.getOrAwaitValueTest()
        assertThat(res.errorMessage).isNotNull()
        assertThat(res.errorMessage).isNotEqualTo(R.string.movies_error)
    }
}
