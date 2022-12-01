package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.CountryEntity

@Dao
interface CountryDao {
    @Query("SELECT * FROM country WHERE name like :name ")
    suspend fun findCountryByName(name: String): CountryEntity

    @Query("SELECT * FROM country WHERE country.id = :id ")
    suspend fun findCountryById(id: Int): CountryEntity

    @Query("DELETE FROM country")
    suspend fun deleteAll()
}