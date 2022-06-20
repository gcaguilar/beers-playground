package com.gcaguilar.authentication

import javax.inject.Inject

class IsLogged @Inject constructor(private val accessTokenStore: AccessTokenStore) {
    suspend operator fun invoke(): Boolean = accessTokenStore.getCode().isNotBlank()
}