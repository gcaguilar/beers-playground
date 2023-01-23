package com.gcaguilar.beer.search_beer.data

import javax.inject.Inject

class FilterDataSource @Inject constructor() {
    var ibu: Int? = null
    var rate: Int? = null
    var abv: Int? = null
    var styles: List<String> = emptyList()
    var countries: List<String> = emptyList()
}