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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesSearchRemoteDataSource(
        @Named("ClientId") clientId: String,
        @Named("ClientSecret") clientSecret: String,
        service: UntappdService,
    ): SearchRemoteDataSource =
        SearchRemoteDataSource(clientId, clientSecret, service)

    @Provides
    @Singleton
    fun providesSearchRepository(searchRemoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(searchRemoteDataSource)
}

