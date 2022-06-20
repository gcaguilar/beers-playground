package com.gcaguilar.authentication

import javax.inject.Inject

class AuthenticationRemoteDataSource @Inject constructor(private val authenticationService: AuthenticationService) {
    suspend fun login(
        clientId: String,
        clientSecret: String,
        redirectUrl: String,
        code: String
    ): String =
        authenticationService.getAccessToken(
            clientId = clientId,
            clientSecret = clientSecret,
            responseType = "code",
            redirect = redirectUrl,
            code = code
        ).response.accessToken
}