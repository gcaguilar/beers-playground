package com.gcaguilar.beers.core_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "beer",
    indices = [Index(value = ["name"])],
    foreignKeys = [ForeignKey(
        entity = BreweryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("brewery_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class BeerEntity(
    @PrimaryKey
    val bid: Int,
    val name: String,
    val abv: String,
    val ibu: Int,
    val image: String,
    val styleName: String,
    @ColumnInfo(name = "brewery_id") val brewery: Int,
)


