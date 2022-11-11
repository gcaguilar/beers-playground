package com.gcaguilar.feature.beer_detail.data

import com.gcaguilar.beer_detail.domain.BeerDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerDetailResponse(
	@Json(name="response")
	val response: Response,
)
@JsonClass(generateAdapter = true)
data class Location(

	@Json(name="brewery_city")
	val breweryCity: String,

	@Json(name="lng")
	val lng: Double,

	@Json(name="brewery_state")
	val breweryState: String,

	@Json(name="lat")
	val lat: Double
)
@JsonClass(generateAdapter = true)
data class Brewery(

	@Json(name="brewery_description")
	val breweryDescription: String?,

	@Json(name="brewery_type")
	val breweryType: String,

	@Json(name="brewery_name")
	val breweryName: String,

	@Json(name="contact")
	val contact: Contact,

	@Json(name="country_name")
	val countryName: String,

	@Json(name="brewery_page_url")
	val breweryPageUrl: String?,

	@Json(name="brewery_slug")
	val brewerySlug: String,

	@Json(name="brewery_label")
	val breweryLabel: String,

	@Json(name="location")
	val location: Location?,

	@Json(name="brewery_id")
	val breweryId: Int
)

@JsonClass(generateAdapter = true)
data class ItemsItem(

	@Json(name="beer")
	val beer: Beer
)

@JsonClass(generateAdapter = true)
data class Vintages(

	@Json(name="count")
	val count: Int,

	@Json(name="items")
	val items: List<ItemsItem>?
)

@JsonClass(generateAdapter = true)
data class BrewedBy(

	@Json(name="count")
	val count: Int,

	@Json(name="items")
	val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Stats(

	@Json(name="monthly_count")
	val monthlyCount: Int,

	@Json(name="user_count")
	val userCount: Int,

	@Json(name="total_count")
	val totalCount: Int,

	@Json(name="total_user_count")
	val totalUserCount: Int
)

@JsonClass(generateAdapter = true)
data class Response(

	@Json(name="beer")
	val beer: Beer
)

@JsonClass(generateAdapter = true)
data class Contact(

	@Json(name="twitter")
	val twitter: String,

	@Json(name="facebook")
	val facebook: String,

	@Json(name="url")
	val url: String
)

@JsonClass(generateAdapter = true)
data class InitTime(

	@Json(name="measure")
	val measure: String,

	@Json(name="time")
	val time: Int
)

@JsonClass(generateAdapter = true)
data class Beer(

	@Json(name="beer_style")
	val beerStyle: String?,

	@Json(name="created_at")
	val createdAt: String?,

	@Json(name="brewed_by")
	val brewedBy: BrewedBy?,

	@Json(name="is_homebrew")
	val isHomebrew: Int?,

	@Json(name="wish_list")
	val wishList: Boolean?,

	@Json(name="weighted_rating_score")
	val weightedRatingScore: Double?,

	@Json(name="beer_slug")
	val beerSlug: String,

	@Json(name="beer_description")
	val beerDescription: String?,

	@Json(name="rating_score")
	val ratingScore: Double?,

	@Json(name="auth_rating")
	val authRating: Int?,

	@Json(name="beer_name")
	val beerName: String,

	@Json(name="rating_count")
	val ratingCount: Int?,

	@Json(name="beer_label")
	val beerLabel: String,

	@Json(name="beer_abv")
	val beerAbv: Double?,

	@Json(name="vintages")
	val vintages: Vintages?,

	@Json(name="stats")
	val stats: Stats?,

	@Json(name="beer_ibu")
	val beerIbu: Int?,

	@Json(name="brewery")
	val brewery: Brewery?,

	@Json(name="bid")
	val bid: Int,

	@Json(name="beer_label_hd")
	val beerLabelHd: String?,

	@Json(name="is_in_production")
	val isInProduction: Int,

	@Json(name="beer_active")
	val beerActive: Int,

	@Json(name="is_variant")
	val isVariant: Int?,

	@Json(name="is_vintage")
	val isVintage: Int?
)

fun Beer.toBeerDetail() = BeerDetail(
	bid = bid,
	description = beerDescription ?: "",
	name = beerName,
	style = beerStyle ?: "",
	photo = beerSlug,
	points = beerAbv ?: 0.0,
)