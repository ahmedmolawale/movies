package com.ahmedmolawale.movies.data.repository

import com.ahmedmolawale.movies.data.fakes.FakeMovieDiscoverRemote
import com.ahmedmolawale.movies.data.fakes.FakeRecentMovieDiscoverCache
import com.ahmedmolawale.movies.data.mapper.MovieEntityMapper
import com.ahmedmolawale.movies.data.model.DummyData
import com.ahmedmolawale.movies.data.util.ResponseType
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.domain.model.Movie
import com.ahmedmolawale.movies.domain.repository.MovieDiscoverRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class MovieDiscoverRepositoryImplTest {

    private lateinit var movieEntityMapper: MovieEntityMapper
    private lateinit var recentMovieDiscoverCache: FakeRecentMovieDiscoverCache
    private lateinit var movieDiscoverRemote: FakeMovieDiscoverRemote
    private lateinit var movieDiscoverRepository: MovieDiscoverRepository

    @Before
    fun setup() {
        movieEntityMapper = MovieEntityMapper()
        movieDiscoverRemote = FakeMovieDiscoverRemote()
        recentMovieDiscoverCache = FakeRecentMovieDiscoverCache()
        movieDiscoverRepository =
            MovieDiscoverRepositoryImpl(
                movieDiscoverRemote,
                recentMovieDiscoverCache,
                movieEntityMapper
            )
    }

    @Test
    fun `check that discoverMovies returns data`() = runBlockingTest {
        movieDiscoverRemote.movieResponseType = ResponseType.DATA
        movieDiscoverRepository.discoverMovies().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }

    @Test
    fun `check that discoverMovies returns correct data`() = runBlockingTest {
        movieDiscoverRemote.movieResponseType = ResponseType.DATA
        movieDiscoverRepository.discoverMovies().collect {
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
    fun `check that discoverMovies saves recent data to cache`() = runBlockingTest {
        movieDiscoverRemote.movieResponseType = ResponseType.DATA
        movieDiscoverRepository.discoverMovies().collect()
        movieDiscoverRepository.getRecentMovieDiscover().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }


    @Test
    fun `check that discoverMovies() returns empty on no data`() = runBlockingTest {
        movieDiscoverRemote.movieResponseType = ResponseType.EMPTY_DATA
        movieDiscoverRepository.discoverMovies().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isEmpty()
        }
    }

    @Test
    fun `check that discoverMovies() returns error`() = runBlockingTest {
        movieDiscoverRemote.movieResponseType = ResponseType.ERROR
        movieDiscoverRepository.discoverMovies().collect {
            assertThat(it).isInstanceOf(Result.Error::class.java)
            it as Result.Error
            assertThat(it.failure).isNotNull()
        }
    }

    @Test
    fun `check that getRecentMovieSearch returns data`() = runBlockingTest {
        recentMovieDiscoverCache.saveMovieDiscoveries(listOf(DummyData.movieEntity))
        movieDiscoverRepository.getRecentMovieDiscover().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isNotEmpty()
        }
    }

    @Test
    fun `check that getRecentMovieSearch returns correct data`() = runBlockingTest {
        recentMovieDiscoverCache.saveMovieDiscoveries(listOf(DummyData.movieEntity))
        movieDiscoverRepository.getRecentMovieDiscover().collect {
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
        movieDiscoverRepository.getRecentMovieDiscover().collect {
            assertThat(it).isInstanceOf(Result.Success::class.java)
            it as Result.Success
            assertThat(it.data).isEmpty()
        }
    }

}