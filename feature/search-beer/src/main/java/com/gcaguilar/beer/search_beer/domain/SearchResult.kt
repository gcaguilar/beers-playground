package com.gcaguilar.beer.search_beer.domain

import com.gcaguilar.beer.search_beer.data.model.Brewery

data class SearchResult(
    val itemFounds: Int,
    val nextPage: Int?,
    val beers: List<Beer>,
)

data class Beer(
    val bid: Int,
    val name: String,
    val style: String,
    val abv: Double,
    val ibu: Int,
    val brewery: Brewery,
    val image: String
)