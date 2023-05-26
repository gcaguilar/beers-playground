package com.gcaguilar.authentication

import javax.inject.Inject

class SaveAccessToken @Inject constructor(
    private val accessTokenStore: AccessTokenStore,
) {
    operator fun invoke(accessToken: String) {
        accessTokenStore.setCode(accessToken)
    }
}