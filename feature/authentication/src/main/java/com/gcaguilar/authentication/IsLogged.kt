package com.gcaguilar.authentication

import javax.inject.Inject

class IsLogged @Inject constructor(private val accessTokenStore: AccessTokenStore) {
    operator fun invoke(): Boolean = accessTokenStore.getCode().isNotBlank()
}