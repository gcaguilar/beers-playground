package com.gcaguilar.untappd.search.di

import com.gcaguilar.untappd.data.SearchRemoteDataSource
import com.gcaguilar.untappd.data.SearchRepositoryImpl
import com.gcaguilar.untappd.data.UntappdService
import com.gcaguilar.untappd.domain.SearchBeer
import com.gcaguilar.untappd.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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
    fun providesSearchRepository(searchRemoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(searchRemoteDataSource)
}

