package com.gcaguilar.beer.search_beer.data

import retrofit2.http.GET
import retrofit2.http.Query

const val API_VERSION = "v4/"
const val SEARCH = "search/"
const val BEER = "beer"
const val LIMIT = 50

interface UntappdService {

    @GET("$API_VERSION$SEARCH$BEER")
    suspend fun searchBeer(
        @Query("q") name: String,
        @Query("limit") limit: Int = LIMIT,
        @Query("offset") offset: Int
    ): BeerResponse
}