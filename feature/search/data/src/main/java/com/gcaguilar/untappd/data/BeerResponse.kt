package com.gcaguilar.untappd.data

import com.gcaguilar.untappd.domain.Beer as ResultBeer
import com.gcaguilar.untappd.domain.Brewery as ResultBrewery
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerResponse(
    @field:Json(name = "response") val response: Response,
)

@JsonClass(generateAdapter = true)
data class Homebrew(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "items") val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Brewery(
    @field:Json(name = "brewery_type") val breweryType: String,
    @field:Json(name = "brewery_name") val breweryName: String,
    @field:Json(name = "contact") val contact: Contact,
    @field:Json(name = "country_name") val countryName: String,
    @field:Json(name = "brewery_page_url") val breweryPageUrl: String,
    @field:Json(name = "brewery_slug") val brewerySlug: String,
    @field:Json(name = "brewery_label") val breweryLabel: String,
    @field:Json(name = "location") val location: Location,
    @field:Json(name = "brewery_id") val breweryId: Int,
    @field:Json(name = "brewery_active") val breweryActive: Int
)

@JsonClass(generateAdapter = true)
data class ItemsItem(
    @field:Json(name = "checkin_count") val checkinCount: Int,
    @field:Json(name = "your_count") val yourCount: Int,
    @field:Json(name = "brewery") val brewery: Brewery,
    @field:Json(name = "have_had") val haveHad: Boolean,
    @field:Json(name = "beer") val beer: Beer
)

@JsonClass(generateAdapter = true)
data class Beers(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "items") val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class Location(
    @field:Json(name = "brewery_city") val breweryCity: String,
    @field:Json(name = "lng") val lng: Double,
    @field:Json(name = "brewery_state") val breweryState: String,
    @field:Json(name = "lat") val lat: Double
)

@JsonClass(generateAdapter = true)
data class Breweries(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "items") val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Contact(
    @field:Json(name = "twitter") val twitter: String,
    @field:Json(name = "facebook") val facebook: String,
    @field:Json(name = "instagram") val instagram: String,
    @field:Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class Response(
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "type_id") val typeId: Int,
    @field:Json(name = "homebrew") val homebrew: Homebrew,
    @field:Json(name = "breweries") val breweries: Breweries,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "search_type") val searchType: String,
    @field:Json(name = "time_taken") val timeTaken: Double,
    @field:Json(name = "search_version") val searchVersion: Int,
    @field:Json(name = "found") val found: Int,
    @field:Json(name = "parsed_term") val parsedTerm: String,
    @field:Json(name = "beers") val beers: Beers,
    @field:Json(name = "limit") val limit: Int,
    @field:Json(name = "term") val term: String,
    @field:Json(name = "brewery_id") val breweryId: Int
)

@JsonClass(generateAdapter = true)
data class Beer(
    @field:Json(name = "beer_label") val beerLabel: String,
    @field:Json(name = "beer_abv") val beerAbv: Double,
    @field:Json(name = "beer_style") val beerStyle: String,
    @field:Json(name = "beer_ibu") val beerIbu: Int,
    @field:Json(name = "created_at") val createdAt: String,
    @field:Json(name = "wish_list") val wishList: Boolean,
    @field:Json(name = "bid") val bid: Int,
    @field:Json(name = "beer_slug") val beerSlug: String,
    @field:Json(name = "in_production") val inProduction: Int,
    @field:Json(name = "beer_description") val beerDescription: String,
    @field:Json(name = "auth_rating") val authRating: Int,
    @field:Json(name = "beer_name") val beerName: String
)

fun ItemsItem.toBeer(): ResultBeer =
    ResultBeer(
        id = beer.bid,
        name = beer.beerName,
        image = beer.beerLabel,
        points = beer.beerAbv,
        brewery = ResultBrewery(
            id = brewery.breweryId,
            name = brewery.breweryName
        )
    )
