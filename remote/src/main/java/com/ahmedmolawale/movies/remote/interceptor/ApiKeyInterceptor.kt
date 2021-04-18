package com.ahmedmolawale.movies.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        val url = request.url.newBuilder().addQueryParameter("api_key", apiKey).build()
        val newRequest: Request = requestBuilder.url(url).build()
        val response: Response?
        try {
            response = chain.proceed(newRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return response
    }
}
