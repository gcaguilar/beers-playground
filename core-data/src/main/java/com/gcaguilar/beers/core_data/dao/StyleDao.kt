package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.StyleEntity

@Dao
interface StyleDao {
    @Query("SELECT * FROM style WHERE name like :name ")
    suspend fun findStyleByName(name: String): StyleEntity

    @Query("SELECT * FROM style WHERE style.id = :id ")
    suspend fun findStyleById(id: Int): StyleEntity
}