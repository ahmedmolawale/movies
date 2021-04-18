package com.ahmedmolawale.core.di

import com.ahmedmolawale.core.BuildConfig
import com.ahmedmolawale.movies.data.contract.remote.MovieDiscoverRemote
import com.ahmedmolawale.movies.data.contract.remote.MovieSearchRemote
import com.ahmedmolawale.movies.remote.ApiService
import com.ahmedmolawale.movies.remote.ApiServiceFactory
import com.ahmedmolawale.movies.remote.contract.MovieDiscoverRemoteImpl
import com.ahmedmolawale.movies.remote.contract.MovieSearchRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @get:Binds
    val MovieDiscoverRemoteImpl.movieDiscoverRemote: MovieDiscoverRemote

    @get:Binds
    val MovieSearchRemoteImpl.movieSearchRemote: MovieSearchRemote

    companion object {
        @[Provides Singleton]
        fun provideApiService(): ApiService =
            ApiServiceFactory.createApiService(BuildConfig.DEBUG, BuildConfig.API_KEY)
    }
}
