package com.gcaguilar.feature.beer_detail.di

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
object NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(@Named("RetrofitClient") retrofit: Retrofit): BeerDetailService =
        retrofit.create(BeerDetailService::class.java)
}