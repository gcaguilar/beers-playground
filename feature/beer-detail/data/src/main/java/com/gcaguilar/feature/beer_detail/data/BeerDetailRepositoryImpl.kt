package com.gcaguilar.feature.beer_detail.data

import com.gcaguilar.beer_detail.domain.BeerDetail
import com.gcaguilar.beer_detail.domain.BeerDetailRepository
import javax.inject.Inject

class BeerDetailRepositoryImpl @Inject constructor(
    private val beerDetailDataSource: BeerDetailDataSource
) : BeerDetailRepository {
    override suspend fun getBeer(bid: Int): BeerDetail = beerDetailDataSource.getBeerById(bid)
}