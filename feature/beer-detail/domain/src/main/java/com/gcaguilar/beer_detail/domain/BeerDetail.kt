package com.gcaguilar.beer_detail.domain

data class BeerDetail(
    val bid: Int,
    val description: String,
    val name: String,
    val style: String,
    val photo: String,
    val points: Double,
)