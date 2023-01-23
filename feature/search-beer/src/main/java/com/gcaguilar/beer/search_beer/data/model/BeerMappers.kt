package com.gcaguilar.beer.search_beer.data.model

import com.gcaguilar.beer.search_beer.data.BeerResponse
import com.gcaguilar.beer.search_beer.data.LIMIT
import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.beer.search_beer.domain.SearchResult
import com.gcaguilar.beers.core_data.models.BeerWithBrewery

fun List<BeerWithBrewery>.toSearchResult(beerResponse: BeerResponse): SearchResult = SearchResult(
    itemFounds = beerResponse.response.found,
    nextPage = if (beerResponse.response.beers.count < beerResponse.response.found) {
        this.size + LIMIT
    } else null,
    beers = this.map { it.toBeer() },
)

fun List<BeerWithBrewery>.toBeers(): List<Beer> = this.map { it.toBeer() }

fun BeerWithBrewery.toBeer() = Beer(
    bid = this.beer.bid,
    name = this.beer.name,
    style = this.beer.name,
    abv = this.beer.abv.toDouble(),
    ibu = this.beer.ibu,
    brewery = this.toBrewery(),
    image = this.beer.image
)

fun BeerWithBrewery.toBrewery() = Brewery(
    id = this.brewery.first().id,
    name = this.brewery.first().name
)