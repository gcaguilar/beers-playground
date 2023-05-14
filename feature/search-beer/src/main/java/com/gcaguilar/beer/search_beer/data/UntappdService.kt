package com.gcaguilar.beer.search_beer.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_VERSION = "v4/"
private const val SEARCH = "search/"
private const val BEER = "beer"
private const val LIMIT = 50
private const val INFO = "info/"
private const val BID = "BID"

interface UntappdService {

    @GET("$API_VERSION$SEARCH$BEER")
    suspend fun searchBeer(
        @Query("q") name: String,
        @Query("limit") limit: Int = LIMIT,
        @Query("offset") offset: Int
    ): BeerResponse

//    @GET("$API_VERSION$BEER$INFO{BID}")
//    suspend fun getBeerByBid(
//        @Path(BID) bid: Int,
//        @Query("compact") compact: Boolean
//    ): BeerDetailResponse
}