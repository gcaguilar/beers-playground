package com.gcaguilar.beer_detail.domain

interface BeerDetailRepository {
    suspend fun getBeer(bid: Int): BeerDetail
}