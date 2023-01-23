package com.gcaguilar.beers.core_data.dao

import androidx.room.Dao
import androidx.room.Query
import com.gcaguilar.beers.core_data.entity.StyleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StyleDao {

    @Query("SELECT * FROM style")
    fun getStyles(): Flow<List<StyleEntity>>

    @Query("SELECT id FROM style WHERE style.checked = 1")
    suspend fun getSelectedIdsOrEmpty(): List<Int>

    @Query("SELECT * FROM style WHERE name like :name")
    fun findStyleByName(name: String): Flow<List<StyleEntity>>

    @Query("SELECT * FROM style WHERE style.id = :id ")
    suspend fun findStyleById(id: Int): StyleEntity

    @Query("UPDATE style SET checked = :checked WHERE style.id = :id")
    suspend fun updateCheckStyle(checked: Boolean, id: Int)

    @Query("UPDATE style SET checked = :checked")
    suspend fun updateAllStyles(checked: Boolean)
}