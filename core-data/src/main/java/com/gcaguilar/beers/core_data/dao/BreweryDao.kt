package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.BreweryEntity

@Dao
interface BreweryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBrewery(vararg brewery: BreweryEntity)

    @Query("SELECT * FROM brewery WHERE name like :name ")
    suspend fun findBreweryByName(name: String): BreweryEntity

    @Query("SELECT * FROM brewery WHERE brewery.id = :id ")
    suspend fun findBreweryById(id: Int): BreweryEntity

    @Query("DELETE FROM brewery")
    suspend fun deleteAll()
}