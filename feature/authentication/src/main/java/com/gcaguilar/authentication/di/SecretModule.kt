package com.gcaguilar.authentication.di

import android.content.Context
import com.gcaguilar.authentication.AccessTokenStore
import com.gcaguilar.authentication.utils.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecretModule {
    private const val CLIENT_ID = "ClientId"
    private const val CLIENT_SECRET = "ClientSecret"

    @Provides
    @Singleton
    @Named(CLIENT_ID)
    fun providesClientId(): String = Keys.clientId()

    @Provides
    @Singleton
    @Named(CLIENT_SECRET)
    fun providesClientSecret(): String = Keys.clientSecret()

    @Provides
    @Singleton
    fun providesAccessTokenStore(@ApplicationContext context: Context) = AccessTokenStore(context)
}