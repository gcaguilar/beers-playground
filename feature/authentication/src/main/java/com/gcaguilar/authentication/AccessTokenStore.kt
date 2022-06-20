package com.gcaguilar.authentication

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val STORE_NAME = "code_data_store"
private const val ACCESS_TOKEN = "access_token"

class AccessTokenStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    fun setCode(code: String) {
        preferences.edit().putString(ACCESS_TOKEN, code).apply()
    }

    fun getCode(): String = preferences.getString(ACCESS_TOKEN, "") ?: ""
}
