package com.gcaguilar.beers.core_data.models

import androidx.room.Embedded
import androidx.room.Relation
import com.gcaguilar.beers.core_data.entity.BeerEntity
import com.gcaguilar.beers.core_data.entity.BreweryEntity

data class BeerWithBrewery(
    @Embedded
    val beer: BeerEntity,
    @Relation(
        parentColumn = "brewery_id",
        entityColumn = "id"
    )
    val brewery: List<BreweryEntity>
)