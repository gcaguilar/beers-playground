package com.gcaguilar.beer.search_beer.domain

data class SearchResult(
    val nextPage: Int?,
    val beers: List<Beer>
)

data class Beer(
    val bid: Int,
    val name: String,
    val style: String,
    val abv: Double,
    val ibu: Int,
    val rate: Double,
    val brewery: Brewery,
    val image: String
)

data class Brewery(
    val id: Int,
    val name: String
)
