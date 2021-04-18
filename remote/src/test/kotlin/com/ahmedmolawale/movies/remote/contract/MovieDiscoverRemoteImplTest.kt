package com.ahmedmolawale.movies.remote.contract

import com.ahmedmolawale.movies.data.contract.remote.MovieDiscoverRemote
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.exception.Failure
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.remote.mapper.MovieRemoteModelMapper
import com.ahmedmolawale.movies.remote.utils.MOVIE_DISCOVER_REQUEST_PATH
import com.ahmedmolawale.movies.remote.utils.MovieRequestDispatcher
import com.ahmedmolawale.movies.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieDiscoverRemoteImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieDiscoverRemote: MovieDiscoverRemote
    private val movieRemoteModelMapper = MovieRemoteModelMapper()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MovieRequestDispatcher().RequestDispatcher()
        mockWebServer.start()
        movieDiscoverRemote =
            MovieDiscoverRemoteImpl(makeTestApiService(mockWebServer), movieRemoteModelMapper)
    }

    @Test
    fun `check that discoverMovies returns movies list`() = runBlocking {
        val movieResult: Result<List<MovieEntity>> = movieDiscoverRemote.discoverMovies()
        assertThat(movieResult).isInstanceOf(Result.Success::class.java)
        movieResult as Result.Success
        val movies: List<MovieEntity> = movieResult.data
        assertThat(movies).isNotEmpty()
    }

    @Test
    fun `check that discoverMovies returns correct data`() = runBlocking {
        val movieResult: Result<List<MovieEntity>> = movieDiscoverRemote.discoverMovies()
        assertThat(movieResult).isInstanceOf(Result.Success::class.java)
        movieResult as Result.Success
        val movies: List<MovieEntity> = movieResult.data
        val firstMovie: MovieEntity = movies.first()
        assertThat(firstMovie.adult).isFalse()
        assertThat(firstMovie.title).isEqualTo("Desire")
        assertThat(firstMovie.originalTitle).isEqualTo("Q")
        assertThat(firstMovie.releaseDate).isEqualTo("2011-09-14")
        assertThat(firstMovie.posterPath).isEqualTo(movieRemoteModelMapper.IMAGE_BASE_URL.plus("/fNSXMHxe1i4VOVJxxwJyGzv2ZTG.jpg"))
        assertThat(firstMovie.backdropPath).isEqualTo(movieRemoteModelMapper.IMAGE_BASE_URL.plus("/q0eXltiQKRqD3qMdN3OC55O06Dy.jpg"))
        assertThat(firstMovie.popularity).isEqualTo(21.799)
    }

    @Test
    fun `check that calling discoverMovies makes request to correct path`() = runBlocking {
        movieDiscoverRemote.discoverMovies()
        assertThat(MOVIE_DISCOVER_REQUEST_PATH)
            .isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling discoverMovies makes a GET request`() = runBlocking {
        movieDiscoverRemote.discoverMovies()
        assertThat("GET $MOVIE_DISCOVER_REQUEST_PATH HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that discoverMovies returns empty list when no movie is found`() =
        runBlocking {
            mockWebServer.dispatcher = MovieRequestDispatcher().EmptyResponseRequestDispatcher()
            val movieResult: Result<List<MovieEntity>> =
                movieDiscoverRemote.discoverMovies()
            movieResult as Result.Success
            val movies: List<MovieEntity> = movieResult.data
            assertThat(movies).isEmpty()
        }

    @Test
    fun `check that discoverMovies returns proper error on bad response from server`() =
        runBlocking {
            mockWebServer.dispatcher = MovieRequestDispatcher().BadRequestDispatcher()
            val movieResult: Result<List<MovieEntity>> =
                movieDiscoverRemote.discoverMovies()
            assertThat(movieResult).isInstanceOf(Result.Error::class.java)
            movieResult as Result.Error
            assertThat(movieResult.failure).isInstanceOf(Failure.ServerError::class.java)
        }

    @Test
    fun `check that discoverMovies returns proper error on server error`() =
        runBlocking {
            mockWebServer.dispatcher = MovieRequestDispatcher().ErrorRequestDispatcher()
            val movieResult: Result<List<MovieEntity>> =
                movieDiscoverRemote.discoverMovies()
            assertThat(movieResult).isInstanceOf(Result.Error::class.java)
            movieResult as Result.Error
            assertThat(movieResult.failure).isInstanceOf(Failure.ServerError::class.java)
        }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
