package com.gcaguilar.untappd.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gcaguilar.authentication.AuthenticationScreen
import com.gcaguilar.beer_detail.presentation.BeerDetailScreen
import com.gcaguilar.untappd.presentation.SearchScreen

sealed class Screen(val route: String) {

    object Authentication : Screen("authentication")
    object Search : Screen("search")
    object BeerDetail : Screen("beerDetail") {
        fun createRoute(root: Screen, bid: Int): String {
            return "${root.route}.$route/$bid"
        }
    }
}

private fun NavGraphBuilder.addSearch(
    modifier: Modifier,
    navController: NavController,
    root: Screen
) {
    composable(
        route = Screen.Search.route
    ) {
        SearchScreen(
            modifier = modifier,
            openBeerDetail = { bid ->
                navController.navigate(
                    Screen.BeerDetail.createRoute(
                        root,
                        bid
                    )
                )
            }
        )
    }
}

private fun NavGraphBuilder.addBeerDetail(
    modifier: Modifier,
    root: Screen,
    bid: Int
) {
    composable(
        route = Screen.BeerDetail.createRoute(root, bid),
        arguments = listOf(
            navArgument("bid") { type = NavType.IntType }
        )
    ) {
        BeerDetailScreen(
            modifier = modifier,
            bid = it.arguments?.getString("bid")?.toInt() ?: 0
        )
    }
}

private fun NavGraphBuilder.addAuthentication(
    modifier: Modifier,
    root: Screen,
) {
    composable(
        route = Screen.Authentication.route
    ) {
        AuthenticationScreen(
            modifier = modifier,
            onUserAuthenticated = {
                Screen.Search.route
            }
        )
    }
}

@Composable
internal fun AppNavigation(modifier: Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Authentication.route
    ) {
        addAuthentication(modifier, Screen.Authentication)
        addSearch(modifier, navController, Screen.Search)
    }
}
