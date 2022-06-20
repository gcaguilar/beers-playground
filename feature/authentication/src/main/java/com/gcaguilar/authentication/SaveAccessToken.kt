package com.gcaguilar.authentication

import javax.inject.Inject
import javax.inject.Named

private const val CODE = "code="
private const val CLIENT_ID = "ClientId"
private const val CLIENT_SECRET = "ClientSecret"

class SaveAccessToken @Inject constructor(
    @Named(CLIENT_ID) private val clientId: String,
    @Named(CLIENT_SECRET) private val clientSecret: String,
    private val accessTokenStore: AccessTokenStore,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
) {
    suspend operator fun invoke(url: String?, redirect: String): Boolean {
        return if (url != null && url.contains(CODE)) {
            val code = url.split(CODE).last()
            val accessToken =
                authenticationRemoteDataSource.login(
                    clientId = clientId,
                    clientSecret = clientSecret,
                    redirectUrl = redirect,
                    code = code
                )
            accessTokenStore.setCode(accessToken)
            true
        } else {
            false
        }
    }
}