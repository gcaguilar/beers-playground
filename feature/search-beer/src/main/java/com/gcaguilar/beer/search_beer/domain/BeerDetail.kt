package com.gcaguilar.beer.search_beer.domain

data class BeerDetail(
    val bid: Int,
    val description: String,
    val name: String,
    val style: String,
    val photo: String,
    val points: Double,
)