package com.gcaguilar.untappd.data

import retrofit2.http.GET
import retrofit2.http.Query

const val API_VERSION = "v4/"
const val SEARCH = "search/"
const val BEER = "beer"

interface UntappdService {

    @GET("${API_VERSION}${SEARCH}${BEER}")
    suspend fun searchBeer(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("q") name: String
    ): BeerResponse
}