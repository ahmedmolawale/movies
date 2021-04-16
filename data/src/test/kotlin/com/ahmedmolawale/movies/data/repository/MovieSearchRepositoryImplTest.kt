package com.ahmedmolawale.movies.data.repository

import com.ahmedmolawale.movies.data.fakes.FakeMovieSearchRemote
import com.ahmedmolawale.movies.data.fakes.FakeRecentMovieSearchCache
import com.ahmedmolawale.movies.data.mapper.MovieEntityMapper
import com.ahmedmolawale.movies.data.model.DummyData
import com.ahmedmolawale.movies.data.util.ResponseType
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieSearchRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieSearchRepositoryImplTest {

    private lateinit var movieEntityMapper: MovieEntityMapper
    private lateinit var recentMovieSearchCache: FakeRecentMovieSearchCache
    private lateinit var movieSearchRemote: FakeMovieSearchRemote
    private lateinit var movieSearchRepository: MovieSearchRepository

    @Before
    fun setup() {
        movieEntityMapper = MovieEntityMapper()
        movieSearchRemote = FakeMovieSearchRemote()
        recentMovieSearchCache = FakeRecentMovieSearchCache()
        movieSearchRepository =
            MovieSearchRepositoryImpl(movieSearchRemote, recentMovieSearchCache, movieEntityMapper)
    }

    @Test
    fun `check that searchMovies returns data`() = runBlockingTest {
        movieSearchRemote.movieResponseType = ResponseType.DATA
        movieSearchRepository.searchMovies(DummyData.searchString).collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }

    @Test
    fun `check that searchMovies returns correct data`() = runBlockingTest {
        movieSearchRemote.movieResponseType = ResponseType.DATA
        movieSearchRepository.searchMovies(DummyData.searchString).collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            val movie: Movie = it.data.first()
            assertThat(movie.title).isEqualTo(DummyData.movie.title)
            assertThat(movie.originalTitle).isEqualTo(DummyData.movie.originalTitle)
            assertThat(movie.releaseDate).isEqualTo(DummyData.movie.releaseDate)
            assertThat(movie.posterPath).isEqualTo(DummyData.movie.posterPath)
            assertThat(movie.backdropPath).isEqualTo(DummyData.movie.backdropPath)
            assertThat(movie.popularity).isEqualTo(DummyData.movie.popularity)
            assertThat(movie.overview).isEqualTo(DummyData.movie.overview)
        }
    }

    @Test
    fun `check that searchMovies saves recent data to cache`() = runBlockingTest {
        movieSearchRemote.movieResponseType = ResponseType.DATA
        movieSearchRepository.searchMovies(DummyData.searchString).collect()
        movieSearchRepository.getRecentMovieSearch().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }

    @Test
    fun `check that searchMovies returns empty on no data`() = runBlockingTest {
        movieSearchRemote.movieResponseType = ResponseType.EMPTY_DATA
        movieSearchRepository.searchMovies(DummyData.searchString).collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isEmpty()
        }
    }

    @Test
    fun `check that searchMovies returns error`() = runBlockingTest {
        movieSearchRemote.movieResponseType = ResponseType.ERROR
        movieSearchRepository.searchMovies(DummyData.searchString).collect {
            assertThat(it).isInstanceOf(Result.Error::class.java)
            it as Result.Error
            assertThat(it.failure).isNotNull()
        }
    }

    @Test
    fun `check that getRecentMovieSearch returns data`() = runBlockingTest {
        recentMovieSearchCache.saveMovieSearch(listOf(DummyData.movieEntity))
        movieSearchRepository.getRecentMovieSearch().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }

    @Test
    fun `check that getRecentMovieSearch returns correct data`() = runBlockingTest {
        recentMovieSearchCache.saveMovieSearch(listOf(DummyData.movieEntity))
        movieSearchRepository.getRecentMovieSearch().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            val movie: Movie = it.data.first()
            assertThat(movie.title).isEqualTo(DummyData.movie.title)
            assertThat(movie.originalTitle).isEqualTo(DummyData.movie.originalTitle)
            assertThat(movie.releaseDate).isEqualTo(DummyData.movie.releaseDate)
            assertThat(movie.posterPath).isEqualTo(DummyData.movie.posterPath)
            assertThat(movie.backdropPath).isEqualTo(DummyData.movie.backdropPath)
            assertThat(movie.popularity).isEqualTo(DummyData.movie.popularity)
            assertThat(movie.overview).isEqualTo(DummyData.movie.overview)
        }
    }

    @Test
    fun `check that getRecentMovieSearch returns empty on no data`() = runBlockingTest {
        movieSearchRepository.getRecentMovieSearch().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isEmpty()
        }
    }

}