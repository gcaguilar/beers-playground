package com.gcaguilar.beers.core_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gcaguilar.beers.core_data.dao.BeerDao
import com.gcaguilar.beers.core_data.dao.BreweryDao
import com.gcaguilar.beers.core_data.dao.CountryDao
import com.gcaguilar.beers.core_data.dao.StyleDao
import com.gcaguilar.beers.core_data.entity.BeerEntity
import com.gcaguilar.beers.core_data.entity.BreweryEntity
import com.gcaguilar.beers.core_data.entity.CountryEntity
import com.gcaguilar.beers.core_data.entity.StyleEntity

@Database(
    entities = [StyleEntity::class, CountryEntity::class, BreweryEntity::class, BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun styleDao(): StyleDao
    abstract fun breweryDao(): CountryDao
    abstract fun countryDao(): BreweryDao
    abstract fun beerDao(): BeerDao
}