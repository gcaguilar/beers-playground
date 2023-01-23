package com.gcaguilar.beer.search_beer.domain

data class SearchFilter(
    val name: String,
    val style: List<String>?,
    val country: List<String>?,
    val rating: Int,
    val abv: Float?,
    val ibu: Int?
)