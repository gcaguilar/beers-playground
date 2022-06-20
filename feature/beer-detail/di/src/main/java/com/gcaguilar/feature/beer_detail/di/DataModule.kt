package com.gcaguilar.feature.beer_detail.di

import com.gcaguilar.beer_detail.domain.BeerDetailRepository
import com.gcaguilar.feature.beer_detail.data.BeerDetailDataSource
import com.gcaguilar.feature.beer_detail.data.BeerDetailRepositoryImpl
import com.gcaguilar.feature.beer_detail.data.BeerDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesBeerDetailRemoteDataSource(service: BeerDetailService): BeerDetailDataSource =
        BeerDetailDataSource(service)

    @Provides
    @Singleton
    fun providesBeerDetailRepository(beerDetailDataSource: BeerDetailDataSource): BeerDetailRepository =
        BeerDetailRepositoryImpl(beerDetailDataSource)
}