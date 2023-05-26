package com.gcaguilar.authentication.di

import android.content.Context
import com.gcaguilar.authentication.AccessTokenStore
import com.gcaguilar.authentication.SaveAccessToken
import com.gcaguilar.authentication.utils.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val CLIENT_ID = "ClientId"
private const val APP_URL = "AppUrl"

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    @Named(CLIENT_ID)
    fun providesClientId(): String = Keys.clientId()

    @Provides
    @Singleton
    @Named(APP_URL)
    fun providesAppUrl(): String = Keys.appUrl()

    @Provides
    @Singleton
    fun providesAccessTokenStore(@ApplicationContext context: Context) = AccessTokenStore(context)

    @Provides
    fun provideSaveAccessToken(
        accessTokenStore: AccessTokenStore,
    ): SaveAccessToken =
        SaveAccessToken(
            accessTokenStore,
        )
}