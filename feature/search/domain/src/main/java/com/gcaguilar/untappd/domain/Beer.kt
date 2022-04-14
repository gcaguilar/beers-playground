package com.gcaguilar.untappd.domain

data class Beer(
    val id: Int,
    val name: String,
    val brewery: Brewery,
    val image: String,
    val points: Double
)