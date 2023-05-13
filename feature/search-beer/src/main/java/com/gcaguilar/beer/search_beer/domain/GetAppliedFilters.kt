package com.gcaguilar.beer.search_beer.domain

import com.gcaguilar.beer.core_data.Country
import com.gcaguilar.beer.search_beer.data.SearchRepository
import javax.inject.Inject

class GetAppliedFilters @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(): AppliedFilterOption {
        val appliedFilter = searchRepository.getFilters()

        return AppliedFilterOption(
            ibu = appliedFilter.ibu,
            abv = appliedFilter.abv,
            rate = appliedFilter.rate,
            countries = getAppliedCountry(appliedFilter.countries),
            styles = getAppliedStyle(appliedFilter.styles)
        )
    }

    private fun getAppliedCountry(items: List<String>): List<CountryOption> {
        val countryOptions = mutableListOf<CountryOption>()
        for (country in Country.values()) {
            val checked = country.name in items
            countryOptions.add(CountryOption(country.value, country.name, checked))
        }
        return countryOptions
    }

    private fun getAppliedStyle(styles: List<String>): List<StyleOption> {
        val styleOption = mutableListOf<StyleOption>()
        for (country in Country.values()) {
            val checked = country.name in styles
            styleOption.add(StyleOption(country.value, country.name, checked))
        }
        return styleOption
    }
}
