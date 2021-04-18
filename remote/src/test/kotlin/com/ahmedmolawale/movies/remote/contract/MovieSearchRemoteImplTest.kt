package com.ahmedmolawale.movies.remote.contract

import com.ahmedmolawale.movies.data.contract.remote.MovieSearchRemote
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.exception.Failure
import com.ahmedmolawale.movies.domain.functional.Result
import com.ahmedmolawale.movies.remote.mapper.MovieRemoteModelMapper
import com.ahmedmolawale.movies.remote.utils.MOVIE_SEARCH_REQUEST_PATH
import com.ahmedmolawale.movies.remote.utils.MovieRequestDispatcher
import com.ahmedmolawale.movies.remote.utils.NO_MATCH_SEARCH_QUERY
import com.ahmedmolawale.movies.remote.utils.SEARCH_QUERY
import com.ahmedmolawale.movies.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieSearchRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieSearchRemote: MovieSearchRemote
    private val movieRemoteModelMapper = MovieRemoteModelMapper()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MovieRequestDispatcher().RequestDispatcher()
        mockWebServer.start()
        movieSearchRemote =
            MovieSearchRemoteImpl(makeTestApiService(mockWebServer), movieRemoteModelMapper)
    }

    @Test
    fun `check that searchMovies returns movies list`() = runBlocking {
        val movieResult: Result<List<MovieEntity>> = movieSearchRemote.searchMovies(SEARCH_QUERY)
        assertThat(movieResult).isInstanceOf(Result.Success::class.java)
        movieResult as Result.Success
        val movies: List<MovieEntity> = movieResult.data
        assertThat(movies).isNotEmpty()
    }

    @Test
    fun `check that searchMovies returns correct data`() = runBlocking {
        val movieResult: Result<List<MovieEntity>> = movieSearchRemote.searchMovies(SEARCH_QUERY)
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
    fun `check that calling searchMovies makes request to correct path`() = runBlocking {
        movieSearchRemote.searchMovies(SEARCH_QUERY)
        assertThat("$MOVIE_SEARCH_REQUEST_PATH?query=$SEARCH_QUERY")
            .isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling searchMovies makes a GET request`() = runBlocking {
        movieSearchRemote.searchMovies(SEARCH_QUERY)
        assertThat("GET $MOVIE_SEARCH_REQUEST_PATH?query=$SEARCH_QUERY HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that searchMovies returns empty list when no movie is found`() =
        runBlocking {
            val movieResult: Result<List<MovieEntity>> =
                movieSearchRemote.searchMovies(NO_MATCH_SEARCH_QUERY)
            movieResult as Result.Success
            val movies: List<MovieEntity> = movieResult.data
            assertThat(movies).isEmpty()
        }

    @Test
    fun `check that searchMovies returns proper error on bad response from server`() =
        runBlocking {
            mockWebServer.dispatcher = MovieRequestDispatcher().BadRequestDispatcher()
            val movieResult: Result<List<MovieEntity>> =
                movieSearchRemote.searchMovies(SEARCH_QUERY)
            assertThat(movieResult).isInstanceOf(Result.Error::class.java)
            movieResult as Result.Error
            assertThat(movieResult.failure).isInstanceOf(Failure.ServerError::class.java)
        }

    @Test
    fun `check that searchMovies returns proper error on server error`() =
        runBlocking {
            mockWebServer.dispatcher = MovieRequestDispatcher().ErrorRequestDispatcher()
            val movieResult: Result<List<MovieEntity>> =
                movieSearchRemote.searchMovies(SEARCH_QUERY)
            assertThat(movieResult).isInstanceOf(Result.Error::class.java)
            movieResult as Result.Error
            assertThat(movieResult.failure).isInstanceOf(Failure.ServerError::class.java)
        }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
