package com.gcaguilar.feature.beer_detail.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_VERSION = "v4/"
const val INFO = "info/"
const val BEER = "beer/"
const val BID = "BID"

interface BeerDetailService {

    @GET("${API_VERSION}${BEER}${INFO}{BID}")
    suspend fun getBeerByBid(@Path(BID) bid: Int, @Query("compact") compact: Boolean): BeerDetailResponse
}