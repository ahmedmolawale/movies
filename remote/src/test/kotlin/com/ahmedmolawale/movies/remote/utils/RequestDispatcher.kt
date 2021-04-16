package com.ahmedmolawale.movies.remote.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class MovieRequestDispatcher {

    /**
     * Return ok response from mock server
     */
    inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "$MOVIE_SEARCH_REQUEST_PATH?query=$SEARCH_QUERY" -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(getJson("response/movie_search.json"))
                }
                "$MOVIE_SEARCH_REQUEST_PATH?query=$NO_MATCH_SEARCH_QUERY" -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(getJson("response/movie_search_no_match.json"))
                }
                MOVIE_DISCOVER_REQUEST_PATH -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(getJson("response/movie_discover.json"))
                }
                else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
            }
        }
    }

    /**
     * Return ok response from mock server with empty data for discover
     */
    inner class EmptyResponseRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                MOVIE_DISCOVER_REQUEST_PATH -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(getJson("response/movie_no_discover.json"))
                }
                else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
            }
        }
    }

    /**
     * Return bad request response from mock server
     */
    internal inner class BadRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest) =
            MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
    }

    /**
     * Return server error response from mock server
     */
    internal inner class ErrorRequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest) =
            MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
    }
}
