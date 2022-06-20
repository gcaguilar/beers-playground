package com.gcaguilar.feature.beer_detail.data

import com.gcaguilar.beer_detail.domain.BeerDetail
import javax.inject.Inject

class BeerDetailDataSource @Inject constructor(private val service: BeerDetailService) {
    suspend fun getBeerById(bid: Int): BeerDetail =
        service.getBeerByBid(bid = bid).response.beer.toBeerDetail()
}