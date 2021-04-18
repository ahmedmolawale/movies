package com.ahmedmolawale.core.di

import com.ahmedmolawale.movies.data.repository.MovieDiscoverRepositoryImpl
import com.ahmedmolawale.movies.data.repository.MovieSearchRepositoryImpl
import com.ahmedmolawale.movies.domain.repository.MovieDiscoverRepository
import com.ahmedmolawale.movies.domain.repository.MovieSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @get:Binds
    val MovieDiscoverRepositoryImpl.movieDiscoverRepository: MovieDiscoverRepository

    @get:Binds
    val MovieSearchRepositoryImpl.movieSearchRepository: MovieSearchRepository
}
