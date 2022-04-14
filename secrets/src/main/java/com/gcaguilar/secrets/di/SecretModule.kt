package com.gcaguilar.secrets.di

import com.gcaguilar.secrets.utils.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecretModule {
    @Provides
    @Singleton
    @Named("ClientId")
    fun providesClientId(): String = Keys.clientId()

    @Provides
    @Singleton
    @Named("ClientSecret")
    fun providesClientSecret(): String = Keys.clientSecret()
}