package com.ahmedmolawale.movies.cache.cacheImpl

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ahmedmolawale.movies.cache.entity.DummyData
import com.ahmedmolawale.movies.cache.mapper.MovieCacheModelMapper
import com.ahmedmolawale.movies.cache.room.MovieDatabase
import com.ahmedmolawale.movies.data.contract.cache.RecentMovieSearchCache
import com.ahmedmolawale.movies.data.model.MovieEntity
import com.ahmedmolawale.movies.domain.functional.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class RecentMovieSearchCacheImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var recentMovieSearchCache: RecentMovieSearchCache
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDatabase = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        recentMovieSearchCache = RecentMovieSearchCacheImpl(
            movieDatabase.moviesDao,
            MovieCacheModelMapper()
        )
    }

    @Test
    fun `check that saveMovieSearch inserts data into database`() = runBlockingTest {
        val movieEntities: List<MovieEntity> = listOf(DummyData.movieEntity)
        recentMovieSearchCache.saveMovieSearch(movieEntities)
        val result = recentMovieSearchCache.getRecentSearch()
        result as Result.Success
        assertThat(result.data).isNotEmpty()
        assertThat(result.data).hasSize(movieEntities.size)
    }

    @Test
    fun `check that saveMovieSearch inserts correct data into database`() = runBlockingTest {
        val movieEntities: List<MovieEntity> = listOf(DummyData.movieEntity)
        recentMovieSearchCache.saveMovieSearch(movieEntities)
        val result = recentMovieSearchCache.getRecentSearch()
        result as Result.Success
        val movieEntity = movieEntities.first()
        val firstMovie = result.data.first()
        assertThat(movieEntity.releaseDate).isEqualTo(firstMovie.releaseDate)
        assertThat(movieEntity.title).isEqualTo(firstMovie.title)
        assertThat(movieEntity.originalTitle).isEqualTo(firstMovie.originalTitle)
        assertThat(movieEntity.popularity).isEqualTo(firstMovie.popularity)
        assertThat(movieEntity.posterPath).isEqualTo(firstMovie.posterPath)
        assertThat(movieEntity.backdropPath).isEqualTo(firstMovie.backdropPath)
        assertThat(movieEntity.overview).isEqualTo(firstMovie.overview)
        assertThat(movieEntity.voteCount).isEqualTo(firstMovie.voteCount)
        assertThat(movieEntity.voteAverage).isEqualTo(firstMovie.voteAverage)
    }

    @Test
    fun `check that saveMovieSearch replaces already saved items`() = runBlocking {
        val movieEntities: List<MovieEntity> = listOf(DummyData.movieEntity)
        val movieEntities2: List<MovieEntity> =
            listOf(DummyData.movieEntity.copy(title = "Olawale"))

        recentMovieSearchCache.saveMovieSearch(movieEntities) //save the first
        recentMovieSearchCache.saveMovieSearch(movieEntities2) //save the second

        val result = recentMovieSearchCache.getRecentSearch()
        result as Result.Success
        assertThat(result.data).isNotEqualTo(movieEntities)
        assertThat(result.data).isEqualTo(movieEntities2)
    }

    @Test
    fun `check that getRecentSearch returns recent search`() = runBlockingTest {
        val movieEntities: List<MovieEntity> = listOf(DummyData.movieEntity)
        recentMovieSearchCache.saveMovieSearch(movieEntities)
        val result = recentMovieSearchCache.getRecentSearch()
        result as Result.Success
        assertThat(result.data).isEqualTo(movieEntities)
    }

    @Test
    fun `check that clearRecentSearch clears all recent search`() = runBlockingTest {
        val movieEntities: List<MovieEntity> = listOf(DummyData.movieEntity)
        recentMovieSearchCache.saveMovieSearch(movieEntities)
        recentMovieSearchCache.clearRecentSearch()
        val result = recentMovieSearchCache.getRecentSearch()
        result as Result.Success
        assertThat(result.data).isEmpty()
    }


    @After
    @Throws(IOException::class)
    fun tearDown() {
        runBlockingTest {
            movieDatabase.clearAllTables()
        }
        movieDatabase.close()
    }
}