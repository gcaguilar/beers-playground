package com.gcaguilar.beer.search_beer.data

import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.beer.search_beer.domain.Brewery
import com.gcaguilar.beer.search_beer.domain.SearchResult

fun BeerResponse.toSearchResult(): SearchResult = SearchResult(
    nextPage = calculateNextPage(response),
    beers = response.beers.toBeerList(),
)

private fun calculateNextPage(response: Response): Int? {
    val offset = response.offset
    val limit = response.limit
    val found = response.found
    val currentCount = offset + response.beers.items.size

    return if (currentCount >= found) {
        null
    } else {
        val nextPage = (currentCount / limit) + 1
        nextPage * limit
    }
}


private fun Beers.toBeerList() = items.map { beer -> beer.toBeer() }

private fun BreweryItemResponse.toBrewery(): Brewery = Brewery(
    id = breweryId,
    name = breweryName,
)

private fun ItemsItem.toBeer(): Beer = Beer(
    bid = beerBeerItemResponse.bid,
    name = beerBeerItemResponse.beerName,
    abv = beerBeerItemResponse.beerAbv,
    ibu = beerBeerItemResponse.beerIbu,
    image = beerBeerItemResponse.beerLabel,
    style = beerBeerItemResponse.beerStyle,
    rate = beerBeerItemResponse.authRating,
    brewery = brewery.toBrewery()
)
