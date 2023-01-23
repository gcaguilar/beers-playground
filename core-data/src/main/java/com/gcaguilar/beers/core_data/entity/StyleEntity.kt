package com.gcaguilar.beers.core_data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "style",
    indices = [Index(value = ["name"])]
)
data class StyleEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val checked: Boolean
)
