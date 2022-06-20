package com.gcaguilar.authentication.di

import com.gcaguilar.authentication.AccessTokenStore
import com.gcaguilar.authentication.AuthenticationRemoteDataSource
import com.gcaguilar.authentication.AuthenticationService
import com.gcaguilar.authentication.SaveAccessToken
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val CLIENT_ID = "ClientId"
private const val CLIENT_SECRET = "ClientSecret"
private const val BASE_URL = "https://untappd.com/"

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService =
        retrofit.create(AuthenticationService::class.java)

    @Provides
    fun provideAuthenticationRemoteDataSource(
        authenticationService: AuthenticationService
    ): AuthenticationRemoteDataSource =
        AuthenticationRemoteDataSource(authenticationService)

    @Provides
    fun provideSaveAccessToken(
        @Named(CLIENT_ID) clientId: String,
        @Named(CLIENT_SECRET) clientSecret: String,
        accessTokenStore: AccessTokenStore,
        authenticationRemoteDataSource: AuthenticationRemoteDataSource
    ): SaveAccessToken =
        SaveAccessToken(
            clientId,
            clientSecret,
            accessTokenStore,
            authenticationRemoteDataSource
        )
}