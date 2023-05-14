package com.gcaguilar.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val LOGIN = "login"
const val AUTHORIZATION = "authorization"

fun NavGraphBuilder.authorizationGraph(navController: NavHostController) {
    navigation(startDestination = LOGIN, route = AUTHORIZATION) {
        composable(LOGIN) {
            AuthenticationScreen()
        }
    }
}