package com.gcaguilar.feature.beer_detail.di

import com.gcaguilar.beer_detail.domain.BeerDetailRepository
import com.gcaguilar.beer_detail.domain.GetBeerByBid
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetBeerByBid(beerDetailRepository: BeerDetailRepository) =
        GetBeerByBid(beerDetailRepository = beerDetailRepository)
}
