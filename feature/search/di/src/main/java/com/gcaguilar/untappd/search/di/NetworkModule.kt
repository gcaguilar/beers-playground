package com.gcaguilar.untappd.search.di

import com.gcaguilar.untappd.data.UntappdService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val BASE_URL = "https://api.untappd.com/"

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().header("User-Agent", "BeersKMM").build())
            }
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): UntappdService =
        retrofit.create(UntappdService::class.java)
}
