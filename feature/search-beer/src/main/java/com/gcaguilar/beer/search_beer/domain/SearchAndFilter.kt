package com.gcaguilar.beer.search_beer.domain

import com.gcaguilar.beer.search_beer.data.AppliedFilters
import com.gcaguilar.beer.search_beer.data.SearchRepository
import javax.inject.Inject

class SearchAndFilter @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(name: String, offset: Int?): SearchResult {
        val searchResult: SearchResult =
            searchRepository.searchBeer(name = name, offset = offset ?: 0)
        val filter: AppliedFilters = searchRepository.getFilters()

        return searchResult.copy(
            beers = filterBeers(filter, searchResult.beers)
        )
    }

    private fun filterBeers(filter: AppliedFilters, beerList: List<Beer>): List<Beer> {
        return beerList.filter { beer ->
            filter.ibu?.let { ibu -> filterByIbu(beer, ibu) } ?: true
        }.filter { beer ->
            filter.abv?.let { abv -> filterByAbv(beer, abv) } ?: true
        }.filter { beer ->
            filter.rate?.let { rate -> filterByRate(beer, rate) } ?: true
        }.filter { beer ->
            filterByStyle(beer, filter.styles)
        }
    }

    private fun filterByIbu(beer: Beer, ibu: Int): Boolean {
        return beer.ibu >= ibu
    }

    private fun filterByAbv(beer: Beer, abv: Int): Boolean {
        return beer.abv >= abv
    }

    private fun filterByRate(beer: Beer, rate: Int): Boolean {
        return beer.rate >= rate
    }

    private fun filterByStyle(beer: Beer, styles: List<String>): Boolean {
        return styles.isEmpty() || styles.contains(beer.style)
    }
}