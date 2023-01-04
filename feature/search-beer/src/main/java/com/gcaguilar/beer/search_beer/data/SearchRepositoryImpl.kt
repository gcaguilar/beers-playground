package com.gcaguilar.beer.search_beer.data

import com.gcaguilar.beer.search_beer.data.model.toSearchResult
import com.gcaguilar.beer.search_beer.data.model.ItemToFilter
import com.gcaguilar.beer.search_beer.data.model.toItemToFilter
import com.gcaguilar.beer.search_beer.domain.SearchFilter
import com.gcaguilar.beer.search_beer.domain.SearchResult
import com.gcaguilar.beers.core_data.dao.BeerDao
import com.gcaguilar.beers.core_data.dao.BreweryDao
import com.gcaguilar.beers.core_data.dao.CountryDao
import com.gcaguilar.beers.core_data.dao.StyleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val beerDao: BeerDao,
    private val breweryDao: BreweryDao,
    private val countryDao: CountryDao,
    private val styleDao: StyleDao,
) {
    suspend fun searchBeer(
        name: String,
        offset: Int,
        filter: SearchFilter? = null
    ): Flow<SearchResult> {
        val response = searchRemoteDataSource.searchBeer(name, offset)
        saveBreweries(response)
        saveBeers(response)
        return beerDao.findBeersByName().map {
            it.toSearchResult(response)
        }
    }

    fun getCountries(filterName: String): Flow<List<ItemToFilter>> {
        return if (filterName.isEmpty()) {
            countryDao.getCountries()
        } else {
            countryDao.findCountryByName(filterName)
        }.map { countryList ->
            countryList.map {  country ->
                country.toItemToFilter()
            }
        }
    }

    fun getStyles(filterName: String): Flow<List<ItemToFilter>> {
        return if (filterName.isEmpty()) {
            styleDao.getStyles()
        } else {
            styleDao.findStyleByName(filterName)
        }.map { styleList ->
            styleList.map { style ->
                style.toItemToFilter()
            }
        }
    }

    suspend fun setStyleChecked(checked: Boolean, id: Int) {
        styleDao.updateCheckStyle(checked, id)
    }

    suspend fun setAllStyleChecked(checked: Boolean) {
        styleDao.updateAllStyles(checked)
    }

    suspend fun setCountryChecked(checked: Boolean, id: Int) {
        countryDao.updateCheckCountry(checked, id)
    }

    suspend fun setAllCountriesChecked(checked: Boolean) {
        countryDao.updateAllCountries(checked)
    }

    private suspend fun saveBreweries(response: BeerResponse) {
        response.toBreweriesEntity().forEach { breweryDao.insertBrewery(it) }
    }

    private suspend fun saveBeers(response: BeerResponse) {
        response.toBeersEntity().forEach { beerDao.insertBeers(it) }
    }
}