package com.ahmedmolawale.movies.remote

import com.ahmedmolawale.movies.remote.model.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/search/movie/")
    suspend fun searchMovies(@Query("query") params: String): Response<MovieResponse>

    @GET("3/discover/movie/")
    suspend fun discoverMovies(): Response<MovieResponse>
}
