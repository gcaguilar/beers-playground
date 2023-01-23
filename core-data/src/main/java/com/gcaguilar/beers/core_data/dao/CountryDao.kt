package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.CountryEntity
import com.gcaguilar.beers.core_data.entity.StyleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("SELECT * FROM country")
    fun getCountries(): Flow<List<CountryEntity>>

    @Query("SELECT id FROM style WHERE style.checked = 1")
    suspend fun getSelectedIdsOrEmpty(): List<Int>

    @Query("SELECT * FROM country WHERE name like :name ")
    fun findCountryByName(name: String): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country WHERE country.id = :id ")
    suspend fun findCountryById(id: Int): CountryEntity

    @Query("UPDATE country SET checked = :checked WHERE country.id = :id")
    suspend fun updateCheckCountry(checked: Boolean, id: Int)

    @Query("UPDATE country SET checked = :checked")
    suspend fun updateAllCountries(checked: Boolean)
}