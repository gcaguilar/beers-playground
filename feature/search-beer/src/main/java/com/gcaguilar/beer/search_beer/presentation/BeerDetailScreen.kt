package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BeerDetailScreen(
    bid: Int
) {
    Text(text = bid.toString())
}