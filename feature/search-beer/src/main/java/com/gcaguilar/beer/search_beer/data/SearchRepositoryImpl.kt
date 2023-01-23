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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val filterDataSource: FilterDataSource
) {
    suspend fun searchBeer(
        offset: Int,
        name: String,
    ): Flow<SearchResult> {
        searchRemoteDataSource.searchBeer(name, offset).response.beers
            .items.filter { itemsItem ->
                with(itemsItem.beer) {
                    filterIbu(this) &&
                            filterAbv(this) &&
                            filterRate(this) &&
                            filterStyle(this)
                } && if (filterDataSource.countries.isNotEmpty()) filterDataSource.countries.contains(
                    itemsItem.brewery.countryName
                ) else true
            }.map {
                itemsItem -> itemsItem.toBeer()
            }
    }

    private fun filterIbu(beer: Beer): Boolean =
        filterDataSource.ibu?.let { beer.beerIbu >= it } ?: true

    private fun filterAbv(beer: Beer): Boolean =
        filterDataSource.abv?.let { beer.beerAbv >= it } ?: true

    private fun filterRate(beer: Beer): Boolean =
        filterDataSource.rate?.let { beer.authRating >= it } ?: true

    private fun filterStyle(beer: Beer): Boolean {
        return if (filterDataSource.styles.isNotEmpty()) {
            filterDataSource.styles.contains(beer.beerStyle)
        } else {
            true
        }
    }

//    fun getCountries(filterName: String): Flow<List<ItemToFilter>> {
//        return if (filterName.isEmpty()) {
//            countryDao.getCountries()
//        } else {
//            countryDao.findCountryByName(filterName)
//        }.map { countryList ->
//            countryList.map { country ->
//                country.toItemToFilter()
//            }
//        }
//    }
//
//    fun getStyles(filterName: String): Flow<List<ItemToFilter>> {
//        return if (filterName.isEmpty()) {
//            styleDao.getStyles()
//        } else {
//            styleDao.findStyleByName(filterName)
//        }.map { styleList ->
//            styleList.map { style ->
//                style.toItemToFilter()
//            }
//        }
//    }
//
//    suspend fun setStyleChecked(checked: Boolean, id: Int) {
//        styleDao.updateCheckStyle(checked, id)
//    }
//
//    suspend fun setAllStyleChecked(checked: Boolean) {
//        styleDao.updateAllStyles(checked)
//    }
//
//    suspend fun setCountryChecked(checked: Boolean, id: Int) {
//        countryDao.updateCheckCountry(checked, id)
//    }
//
//    suspend fun setAllCountriesChecked(checked: Boolean) {
//        countryDao.updateAllCountries(checked)
//    }
//
//    private suspend fun saveBreweries(response: BeerResponse) {
//        response.toBreweriesEntity().forEach { breweryDao.insertBrewery(it) }
//    }
//
//    private suspend fun saveBeers(response: BeerResponse) {
//        response.toBeersEntity().forEach { beerDao.insertBeers(it) }
//    }
}