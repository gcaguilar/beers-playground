package com.gcaguilar.beer.search_beer.data

import com.gcaguilar.beer.search_beer.domain.SearchResult
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val filtersCache: FiltersCache
) {
    suspend fun searchBeer(
        offset: Int,
        name: String,
    ): SearchResult = searchRemoteDataSource.searchBeer(name, offset).toSearchResult()

    fun getFilters(): AppliedFilters = filtersCache.getAppliedFilters()

    fun updateIbu(ibu: Int) {
        filtersCache.updateIbu(ibu)
    }

    fun updateRate(rate: Int) {
        filtersCache.updateRate(rate)
    }

    fun updateAbv(abv: Int) {
        filtersCache.updateAbv(abv)
    }

    fun updateCountries(countries: List<String>) {
        filtersCache.updateCountries(countries)
    }

    fun updateStyles(styles: List<String>) {
        filtersCache.updateStyles(styles)
    }
}