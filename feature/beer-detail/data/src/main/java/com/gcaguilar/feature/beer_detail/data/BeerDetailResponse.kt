package com.gcaguilar.feature.beer_detail.data

import com.gcaguilar.beer_detail.domain.BeerDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerDetailResponse(

    @Json(name = "meta")
    val meta: Meta,

    @Json(name = "response")
    val response: Response,

    @Json(name = "notifications")
    val notifications: List<Any>
)

@JsonClass(generateAdapter = true)
data class Photo(

    @Json(name = "photo_img_sm")
    val photoImgSm: String,

    @Json(name = "photo_img_md")
    val photoImgMd: String,

    @Json(name = "photo_img_og")
    val photoImgOg: String,

    @Json(name = "photo_img_lg")
    val photoImgLg: String
)

@JsonClass(generateAdapter = true)
data class Toasts(

    @Json(name = "total_count")
    val totalCount: Int,

    @Json(name = "count")
    val count: Int,

    @Json(name = "auth_toast")
    val authToast: Any,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class BadgeImage(

    @Json(name = "md")
    val md: String,

    @Json(name = "sm")
    val sm: String,

    @Json(name = "lg")
    val lg: String
)

@JsonClass(generateAdapter = true)
data class ResponseTime(

    @Json(name = "measure")
    val measure: String,

    @Json(name = "time")
    val time: Double
)

@JsonClass(generateAdapter = true)
data class Categories(

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class Similar(

    @Json(name = "method")
    val method: String,

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class Source(

    @Json(name = "app_name")
    val appName: String,

    @Json(name = "app_website")
    val appWebsite: String
)

@JsonClass(generateAdapter = true)
data class InitTime(

    @Json(name = "measure")
    val measure: String,

    @Json(name = "time")
    val time: Int
)

@JsonClass(generateAdapter = true)
data class Venue(

    @Json(name = "venue_name")
    val venueName: String,

    @Json(name = "venue_icon")
    val venueIcon: VenueIcon,

    @Json(name = "parent_category_id")
    val parentCategoryId: String,

    @Json(name = "venue_slug")
    val venueSlug: String,

    @Json(name = "primary_category")
    val primaryCategory: String,

    @Json(name = "contact")
    val contact: Contact,

    @Json(name = "foursquare")
    val foursquare: Foursquare,

    @Json(name = "primary_category_key")
    val primaryCategoryKey: String,

    @Json(name = "location")
    val location: Location,

    @Json(name = "categories")
    val categories: Categories,

    @Json(name = "is_verified")
    val isVerified: Boolean,

    @Json(name = "venue_id")
    val venueId: Int
)

@JsonClass(generateAdapter = true)
data class Badges(

    @Json(name = "retro_status")
    val retroStatus: Boolean,

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Location(

    @Json(name = "brewery_city")
    val breweryCity: String,

    @Json(name = "lng")
    val lng: Double,

    @Json(name = "brewery_state")
    val breweryState: String,

    @Json(name = "lat")
    val lat: Double,

    @Json(name = "venue_state")
    val venueState: String?,

    @Json(name = "venue_address")
    val venueAddress: String?,

    @Json(name = "venue_city")
    val venueCity: String?,

    @Json(name = "venue_country")
    val venueCountry: String?
)

@JsonClass(generateAdapter = true)
data class BrewedBy(

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Media(

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class User(

    @Json(name = "is_private")
    val isPrivate: Int,

    @Json(name = "uid")
    val uid: Int,

    @Json(name = "user_avatar")
    val userAvatar: String,

    @Json(name = "user_name")
    val userName: String,

    @Json(name = "last_name")
    val lastName: String,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "bio")
    val bio: String,

    @Json(name = "location")
    val location: String,

    @Json(name = "is_supporter")
    val isSupporter: Int,

    @Json(name = "relationship")
    val relationship: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "account_type")
    val accountType: String,

    @Json(name = "brewery_details")
    val breweryDetails: List<Any>,

    @Json(name = "venue_details")
    val venueDetails: List<Any>
)

@JsonClass(generateAdapter = true)
data class Brewery(

    @Json(name = "brewery_type")
    val breweryType: String,

    @Json(name = "brewery_name")
    val breweryName: String,

    @Json(name = "contact")
    val contact: Contact,

    @Json(name = "country_name")
    val countryName: String,

    @Json(name = "brewery_page_url")
    val breweryPageUrl: String,

    @Json(name = "brewery_slug")
    val brewerySlug: String,

    @Json(name = "brewery_label")
    val breweryLabel: String,

    @Json(name = "location")
    val location: Location,

    @Json(name = "brewery_id")
    val breweryId: Int,

    @Json(name = "brewery_active")
    val breweryActive: Int
)

@JsonClass(generateAdapter = true)
data class Foursquare(

    @Json(name = "foursquare_url")
    val foursquareUrl: String,

    @Json(name = "foursquare_id")
    val foursquareId: String
)

@JsonClass(generateAdapter = true)
data class Vintages(

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class VenueItem(

    @Json(name = "venue_name")
    val venueName: String,

    @Json(name = "venue_icon")
    val venueIcon: VenueIcon,

    @Json(name = "parent_category_id")
    val parentCategoryId: String,

    @Json(name = "venue_slug")
    val venueSlug: String,

    @Json(name = "primary_category")
    val primaryCategory: String,

    @Json(name = "contact")
    val contact: Contact,

    @Json(name = "foursquare")
    val foursquare: Foursquare,

    @Json(name = "location")
    val location: Location,

    @Json(name = "categories")
    val categories: Categories,

    @Json(name = "is_verified")
    val isVerified: Int,

    @Json(name = "venue_id")
    val venueId: Int
)

@JsonClass(generateAdapter = true)
data class Meta(

    @Json(name = "code")
    val code: Int,

    @Json(name = "init_time")
    val initTime: InitTime,

    @Json(name = "response_time")
    val responseTime: ResponseTime
)

@JsonClass(generateAdapter = true)
data class Pagination(

    @Json(name = "since_url")
    val sinceUrl: String,

    @Json(name = "next_url")
    val nextUrl: String,

    @Json(name = "max_id")
    val maxId: Int
)

@JsonClass(generateAdapter = true)
data class Beer(

    @Json(name = "beer_style")
    val beerStyle: String,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "wish_list")
    val wishList: Boolean,

    @Json(name = "media")
    val media: Media,

    @Json(name = "weighted_rating_score")
    val weightedRatingScore: Double,

    @Json(name = "beer_slug")
    val beerSlug: String,

    @Json(name = "beer_description")
    val beerDescription: String,

    @Json(name = "beer_label")
    val beerLabel: String,

    @Json(name = "stats")
    val stats: Stats,

    @Json(name = "brewery")
    val brewery: Brewery,

    @Json(name = "beer_label_hd")
    val beerLabelHd: String,

    @Json(name = "is_in_production")
    val isInProduction: Int,

    @Json(name = "beer_active")
    val beerActive: Int,

    @Json(name = "similar")
    val similar: Similar,

    @Json(name = "brewed_by")
    val brewedBy: BrewedBy,

    @Json(name = "is_homebrew")
    val isHomebrew: Int,

    @Json(name = "rating_score")
    val ratingScore: Double,

    @Json(name = "auth_rating")
    val authRating: Int,

    @Json(name = "friends")
    val friends: Friends,

    @Json(name = "beer_name")
    val beerName: String,

    @Json(name = "rating_count")
    val ratingCount: Int,

    @Json(name = "beer_abv")
    val beerAbv: Double,

    @Json(name = "vintages")
    val vintages: Vintages,

    @Json(name = "beer_ibu")
    val beerIbu: Int,

    @Json(name = "bid")
    val bid: Int,

    @Json(name = "checkins")
    val checkins: Checkins,

    @Json(name = "count")
    val count: Int,

    @Json(name = "on_list")
    val onList: Boolean,

    @Json(name = "has_had")
    val hasHad: Boolean,

    @Json(name = "beer_style_id")
    val beerStyleId: Int,

    @Json(name = "is_variant")
    val isVariant: Int,

    @Json(name = "is_vintage")
    val isVintage: Int
)

@JsonClass(generateAdapter = true)
data class Comments(

    @Json(name = "total_count")
    val totalCount: Int,

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class Friends(

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<Any>
)

@JsonClass(generateAdapter = true)
data class ItemsItem(

    @Json(name = "venue")
    val venue: List<VenueItem>,

    @Json(name = "photo_id")
    val photoId: Int,

    @Json(name = "checkin_id")
    val checkinId: Int,

    @Json(name = "brewery")
    val brewery: Brewery,

    @Json(name = "photo")
    val photo: Photo,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "user")
    val user: User,

    @Json(name = "beer")
    val beer: Beer,

    @Json(name = "category_key")
    val categoryKey: String,

    @Json(name = "category_name")
    val categoryName: String,

    @Json(name = "category_id")
    val categoryId: String,

    @Json(name = "is_primary")
    val isPrimary: Boolean,

    @Json(name = "rating_score")
    val ratingScore: Double,

    @Json(name = "friends")
    val friends: Friends,

    @Json(name = "comments")
    val comments: Comments,

    @Json(name = "checkin_comment")
    val checkinComment: String,

    @Json(name = "media")
    val media: Media,

    @Json(name = "source")
    val source: Source,

    @Json(name = "toasts")
    val toasts: Toasts,

    @Json(name = "badges")
    val badges: Badges,

    @Json(name = "uid")
    val uid: Int,

    @Json(name = "like_id")
    val likeId: Int,

    @Json(name = "like_owner")
    val likeOwner: Boolean,

    @Json(name = "badge_description")
    val badgeDescription: String,

    @Json(name = "badge_id")
    val badgeId: Int,

    @Json(name = "user_badge_id")
    val userBadgeId: Int,

    @Json(name = "badge_name")
    val badgeName: String,

    @Json(name = "badge_image")
    val badgeImage: BadgeImage
)

@JsonClass(generateAdapter = true)
data class VenueIcon(

    @Json(name = "md")
    val md: String,

    @Json(name = "sm")
    val sm: String,

    @Json(name = "lg")
    val lg: String
)

@JsonClass(generateAdapter = true)
data class Contact(

    @Json(name = "twitter")
    val twitter: String?,

    @Json(name = "facebook")
    val facebook: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "venue_url")
    val venueUrl: String?,

    @Json(name = "instagram")
    val instagram: String?
)

@JsonClass(generateAdapter = true)
data class Checkins(

    @Json(name = "pagination")
    val pagination: Pagination,

    @Json(name = "count")
    val count: Int,

    @Json(name = "items")
    val items: List<ItemsItem>
)

@JsonClass(generateAdapter = true)
data class Stats(

    @Json(name = "monthly_count")
    val monthlyCount: Int,

    @Json(name = "user_count")
    val userCount: Int,

    @Json(name = "total_count")
    val totalCount: Int,

    @Json(name = "total_user_count")
    val totalUserCount: Int
)

@JsonClass(generateAdapter = true)
data class Response(

    @Json(name = "beer")
    val beer: Beer
)
fun Beer.toBeerDetail() = BeerDetail(
    bid = bid,
    description = beerDescription,
    name = beerName,
    style = beerStyle,
    photo = beerSlug,
    points = beerAbv,
)