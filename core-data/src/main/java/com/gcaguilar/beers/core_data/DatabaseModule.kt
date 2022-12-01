package com.gcaguilar.beers.core_data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideBeersDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        BeerDatabase::class.java,
        "beers"
    ).build()

    @Singleton
    @Provides
    fun provideCountryDao(db: BeerDatabase) = db.countryDao()

    @Singleton
    @Provides
    fun provideStyleDao(db: BeerDatabase) = db.styleDao()

    @Singleton
    @Provides
    fun provideBreweryDao(db: BeerDatabase) = db.breweryDao()

    @Singleton
    @Provides
    fun provideBeerDao(db: BeerDatabase) = db.beerDao()
}