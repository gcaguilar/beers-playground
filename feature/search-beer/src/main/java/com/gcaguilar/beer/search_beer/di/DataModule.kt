package com.gcaguilar.beer.search_beer.di

import com.gcaguilar.beer.search_beer.data.SearchRemoteDataSource
import com.gcaguilar.beer.search_beer.data.SearchRepository
import com.gcaguilar.beer.search_beer.data.UntappdService
import com.gcaguilar.beers.core_data.dao.BeerDao
import com.gcaguilar.beers.core_data.dao.BreweryDao
import com.gcaguilar.beers.core_data.dao.StyleDao
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
    @Singleton
    @Provides
    fun provideApiService(@Named("RetrofitClient") retrofit: Retrofit): UntappdService =
        retrofit.create(UntappdService::class.java)

    @Provides
    @Singleton
    fun providesSearchRemoteDataSource(service: UntappdService): SearchRemoteDataSource =
        SearchRemoteDataSource(service)

    @Provides
    @Singleton
    fun providesSearchRepository(
        searchRemoteDataSource: SearchRemoteDataSource,
        breweryDao: BreweryDao,
        beerDao: BeerDao,
        styleDao: StyleDao
    ): SearchRepository =
        SearchRepository(searchRemoteDataSource, beerDao, breweryDao, styleDao)
}

