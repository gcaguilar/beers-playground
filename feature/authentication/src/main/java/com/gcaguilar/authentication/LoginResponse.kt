package com.gcaguilar.authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(

	@Json(name="meta")
	val meta: Meta,

	@Json(name="response")
	val response: Response
)

@JsonClass(generateAdapter = true)

data class Response(

	@Json(name="access_token")
	val accessToken: String
)
@JsonClass(generateAdapter = true)
data class Meta(

	@Json(name="http_code")
	val httpCode: Int
)
