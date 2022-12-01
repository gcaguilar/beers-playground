package com.gcaguilar.beers.core_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "brewery",
    indices = [Index(value = ["name"])]
)
data class BreweryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "brewery_page") val url: String
)