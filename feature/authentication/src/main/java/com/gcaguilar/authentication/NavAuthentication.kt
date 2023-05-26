package com.gcaguilar.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val LOGIN = "login"
const val AUTHORIZATION_ROUTE = "authorizationRoute"

fun NavGraphBuilder.authorizationGraph() {
    navigation(startDestination = LOGIN, route = AUTHORIZATION_ROUTE) {
        composable(LOGIN) {
            AuthenticationScreen()
        }
    }
}