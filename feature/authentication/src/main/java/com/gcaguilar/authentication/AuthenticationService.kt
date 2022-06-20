package com.gcaguilar.authentication

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

private const val CLIENT_ID_PARAMETER = "client_id"
private const val CLIENT_SECRET_PARAMETER = "client_secret"
private const val RESPONSE_TYPE = "response_type"
private const val REDIRECT_URL = "redirect_url"
private const val CODE = "code"
private const val ENDPOINT = "oauth/authorize/"

interface AuthenticationService {
    @GET(ENDPOINT)
    suspend fun getAccessToken(
        @Query(CLIENT_ID_PARAMETER) clientId: String,
        @Query(CLIENT_SECRET_PARAMETER) clientSecret: String,
        @Query(RESPONSE_TYPE) responseType: String,
        @Query(REDIRECT_URL) redirect: String,
        @Query(CODE) code: String,
    ): LoginResponse
}