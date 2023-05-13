package com.gcaguilar.beer.search_beer.domain

data class AppliedFilterOption(
    val ibu: Int?,
    val rate: Int?,
    val abv: Int?,
    val countries: List<CountryOption>,
    val styles: List<StyleOption>
)

interface FilterItemOption {
    val id: String
    val name: String
    val checked: Boolean
}

data class CountryOption(
    override val id: String,
    override val name: String,
    override val checked: Boolean,
) : FilterItemOption

data class StyleOption(
    override val id: String,
    override val name: String,
    override val checked: Boolean
) : FilterItemOption
