package com.gcaguilar.beer.search_beer.data

import javax.inject.Inject

data class AppliedFilters(
    val ibu: Int?,
    val rate: Int?,
    val abv: Int?,
    val countries: List<String>,
    val styles: List<String>
)

class FiltersCache @Inject constructor() {
    private var appliedFilters = AppliedFilters(null, null, null, emptyList(), emptyList())

    fun getAppliedFilters(): AppliedFilters = appliedFilters

    fun updateIbu(ibu: Int) {
        appliedFilters = appliedFilters.copy(ibu = ibu)
    }

    fun updateRate(rate: Int) {
        appliedFilters = appliedFilters.copy(rate = rate)
    }

    fun updateAbv(abv: Int) {
        appliedFilters = appliedFilters.copy(abv = abv)
    }

    fun updateCountries(countries: List<String>) {
        appliedFilters = appliedFilters.copy(countries = countries)
    }

    fun updateStyles(styles: List<String>) {
        appliedFilters = appliedFilters.copy(styles = styles)
    }
}
