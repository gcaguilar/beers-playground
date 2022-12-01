package com.gcaguilar.beer.search_beer.data

import com.gcaguilar.beer.search_beer.data.model.toSearchResult
import com.gcaguilar.beer.search_beer.domain.SearchResult
import com.gcaguilar.beers.core_data.dao.BeerDao
import com.gcaguilar.beers.core_data.dao.BreweryDao
import com.gcaguilar.beers.core_data.dao.StyleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val beerDao: BeerDao,
    private val breweryDao: BreweryDao,
    private val styleDao: StyleDao,
) {
    suspend fun searchBeer(name: String, offset: Int): Flow<SearchResult> {
        val response = searchRemoteDataSource.searchBeer(name, offset)
        response.toBreweriesEntity().forEach { breweryDao.insertBrewery(it) }
        response.toBeersEntity().forEach { beerDao.insertBeers(it) }
        return beerDao.findBeersByName().map {
            it.toSearchResult(response)
        }
    }
}