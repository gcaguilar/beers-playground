package com.gcaguilar.untappd.search.di

import com.gcaguilar.untappd.domain.SearchBeer
import com.gcaguilar.untappd.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideSearchBeer(searchRepository: SearchRepository) =
        SearchBeer(searchRepository = searchRepository)
}
