package com.ahmedmolawale.movies.cache.di

import android.content.Context
import androidx.room.Room
import com.ahmedmolawale.movies.cache.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {

    companion object {
        @Singleton
        @Provides
        fun provideDataBase(@ApplicationContext context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                MovieDatabase.DB_NAME
            ).fallbackToDestructiveMigration().build()
        }

        @Singleton
        @Provides
        fun provideMoviesDao(movieDatabase: MovieDatabase) = movieDatabase.moviesDao
    }
}