package com.gcaguilar.beer.search_beer.data

import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val service: UntappdService
) {


    suspend fun searchBeer(name: String, offset: Int): BeerResponse =
        service.searchBeer(name = name, offset = offset)
}