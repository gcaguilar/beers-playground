package com.gcaguilar.untappd.interceptor

import android.util.Log
import com.gcaguilar.authentication.AccessTokenStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val USER_AGENT = "User-Agent"
private const val USER_AGENT_NAME = "BeersKMM"
private const val ACCESS_TOKEN = "access_token"

class AuthenticationInterceptor @Inject constructor(private val accessTokenStore: AccessTokenStore) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url
        val accessToken = accessTokenStore.getCode()

        return if (accessToken.isNotEmpty()) {
            val newHttpUrl =
                httpUrl.newBuilder().addQueryParameter(ACCESS_TOKEN, accessToken).build()

            val newRequest = original.newBuilder()
                .url(newHttpUrl)
                .header(
                    USER_AGENT,
                    USER_AGENT_NAME
                ).build()

            chain.proceed(newRequest)
        } else {
            chain.proceed(
                chain.request().newBuilder().header(
                    USER_AGENT,
                    USER_AGENT_NAME
                ).build()
            )
        }
    }
}