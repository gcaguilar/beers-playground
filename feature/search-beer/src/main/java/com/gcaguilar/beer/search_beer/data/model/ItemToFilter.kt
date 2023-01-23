package com.gcaguilar.beer.search_beer.data.model

import com.gcaguilar.beers.core_data.entity.CountryEntity
import com.gcaguilar.beers.core_data.entity.StyleEntity

data class ItemToFilter(
    val id: Int,
    val name: String,
    val checked: Boolean
)

fun CountryEntity.toItemToFilter() = ItemToFilter(
    id = this.id,
    name = this.name,
    checked = false
)

fun StyleEntity.toItemToFilter() = ItemToFilter(
    id = this.id,
    name = this.name,
    checked = this.checked
)