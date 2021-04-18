package com.ahmedmolawale.movies.cache.di

import android.content.Context
import androidx.room.Room
import com.ahmedmolawale.movies.cache.cacheImpl.RecentMovieDiscoverCacheImpl
import com.ahmedmolawale.movies.cache.cacheImpl.RecentMovieSearchCacheImpl
import com.ahmedmolawale.movies.cache.room.MovieDatabase
import com.ahmedmolawale.movies.cache.room.MoviesDao
import com.ahmedmolawale.movies.data.contract.cache.RecentMovieDiscoverCache
import com.ahmedmolawale.movies.data.contract.cache.RecentMovieSearchCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {

    @get:Binds
    val RecentMovieSearchCacheImpl.recentMovieSearchCache: RecentMovieSearchCache

    @get:Binds
    val RecentMovieDiscoverCacheImpl.recentMovieDiscoverCache: RecentMovieDiscoverCache

    companion object {
        @[Provides Singleton]
        fun provideDataBase(@ApplicationContext context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                MovieDatabase.DB_NAME
            ).fallbackToDestructiveMigration().build()
        }

        @[Provides Singleton]
        fun provideMoviesDao(movieDatabase: MovieDatabase): MoviesDao = movieDatabase.moviesDao
    }
}
