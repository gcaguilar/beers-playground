package com.gcaguilar.untappd.data

import com.gcaguilar.untappd.domain.Beer as ResultBeer
import com.gcaguilar.untappd.domain.Brewery as ResultBrewery
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerResponse(
    @Json(name = "response") val response: Response,
)

@JsonClass(generateAdapter = true)
data class Homebrew(
    @Json(name = "count") val count: Int,
    @Json(name = "items") val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Brewery(
    @Json(name = "brewery_type") val breweryType: String,
    @Json(name = "brewery_name") val breweryName: String,
    @Json(name = "contact") val contact: Contact,
    @Json(name = "country_name") val countryName: String,
    @Json(name = "brewery_page_url") val breweryPageUrl: String,
    @Json(name = "brewery_slug") val brewerySlug: String,
    @Json(name = "brewery_label") val breweryLabel: String,
    @Json(name = "location") val location: Location,
    @Json(name = "brewery_id") val breweryId: Int,
    @Json(name = "brewery_active") val breweryActive: Int
)

@JsonClass(generateAdapter = true)
data class ItemsItem(
    @Json(name = "checkin_count") val checkinCount: Int,
    @Json(name = "your_count") val yourCount: Int,
    @Json(name = "brewery") val brewery: Brewery,
    @Json(name = "have_had") val haveHad: Boolean,
    @Json(name = "beer") val beer: Beer
)

@JsonClass(generateAdapter = true)
data class Beers(
    @Json(name = "count") val count: Int,
    @Json(name = "items") val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "brewery_city") val breweryCity: String,
    @Json(name = "lng") val lng: Double,
    @Json(name = "brewery_state") val breweryState: String,
    @Json(name = "lat") val lat: Double
)

@JsonClass(generateAdapter = true)
data class Breweries(
    @Json(name = "count") val count: Int,
    @Json(name = "items") val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "twitter") val twitter: String,
    @Json(name = "facebook") val facebook: String,
    @Json(name = "instagram") val instagram: String,
    @Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "offset") val offset: Int,
    @Json(name = "type_id") val typeId: Int,
    @Json(name = "homebrew") val homebrew: Homebrew,
    @Json(name = "breweries") val breweries: Breweries,
    @Json(name = "message") val message: String,
    @Json(name = "search_type") val searchType: String,
    @Json(name = "time_taken") val timeTaken: Double,
    @Json(name = "search_version") val searchVersion: Int,
    @Json(name = "found") val found: Int,
    @Json(name = "parsed_term") val parsedTerm: String,
    @Json(name = "beers") val beers: Beers,
    @Json(name = "limit") val limit: Int,
    @Json(name = "term") val term: String,
    @Json(name = "brewery_id") val breweryId: Int
)

@JsonClass(generateAdapter = true)
data class Beer(
    @Json(name = "beer_label") val beerLabel: String,
    @Json(name = "beer_abv") val beerAbv: Double,
    @Json(name = "beer_style") val beerStyle: String,
    @Json(name = "beer_ibu") val beerIbu: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "wish_list") val wishList: Boolean,
    @Json(name = "bid") val bid: Int,
    @Json(name = "beer_slug") val beerSlug: String,
    @Json(name = "in_production") val inProduction: Int,
    @Json(name = "beer_description") val beerDescription: String,
    @Json(name = "auth_rating") val authRating: Double,
    @Json(name = "beer_name") val beerName: String
)

fun ItemsItem.toBeer(): ResultBeer =
    ResultBeer(
        bid = beer.bid,
        name = beer.beerName,
        style = beer.beerStyle,
        image = beer.beerLabel,
        points = beer.beerAbv,
        brewery = ResultBrewery(
            id = brewery.breweryId,
            name = brewery.breweryName
        )
    )
