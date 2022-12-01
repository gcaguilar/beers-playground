package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.BeerEntity
import com.gcaguilar.beers.core_data.models.BeerWithBrewery
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBeers(vararg beers: BeerEntity)

    //WHERE beer.name like '%' || :name || '%'
    @Query("SELECT * FROM beer inner join brewery on beer.brewery_id = brewery.id")
    fun findBeersByName(): Flow<List<BeerWithBrewery>>


    @Query("DELETE FROM beer")
    suspend fun deleteAll()
}