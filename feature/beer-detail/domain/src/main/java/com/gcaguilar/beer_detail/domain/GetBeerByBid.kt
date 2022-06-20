package com.gcaguilar.beer_detail.domain

import javax.inject.Inject

class GetBeerByBid @Inject constructor(
    private val beerDetailRepository: BeerDetailRepository
) {
    suspend operator fun invoke(bid: Int) = beerDetailRepository.getBeer(bid)
}