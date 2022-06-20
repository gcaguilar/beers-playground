package com.gcaguilar.untappd.domain

data class Beer(
    val bid: Int,
    val name: String,
    val style: String,
    val brewery: Brewery,
    val image: String,
    val points: Double
)