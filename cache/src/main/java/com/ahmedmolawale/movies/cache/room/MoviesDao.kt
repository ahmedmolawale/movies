package com.ahmedmolawale.movies.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmolawale.movies.cache.entity.MovieCacheModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieCacheModelList: List<MovieCacheModel>)

    @Query("SELECT * FROM movies WHERE isDiscovered = 0")
    suspend fun fetchRecentSearch(): List<MovieCacheModel>

    @Query("SELECT * FROM movies WHERE isDiscovered = 1")
    suspend fun fetchRecentDiscover(): List<MovieCacheModel>

    @Query("DELETE FROM movies WHERE isDiscovered = 1")
    suspend fun clearRecentDiscover()

    @Query("DELETE FROM movies WHERE isDiscovered = 0")
    suspend fun clearRecentSearch()
}
