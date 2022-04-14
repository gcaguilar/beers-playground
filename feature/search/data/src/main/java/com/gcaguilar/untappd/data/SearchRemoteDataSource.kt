package com.gcaguilar.untappd.data

import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val clientId: String,
    private val clientSecret: String,
    private val service: UntappdService
) {


    suspend fun searchBeer(name: String): BeerResponse =
        service.searchBeer(name = name, clientId = clientId, clientSecret = clientSecret)
}