package com.gcaguilar.beers.core_data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "country",
    indices = [Index(value = ["name"])]
)
data class CountryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val checked: Boolean
)