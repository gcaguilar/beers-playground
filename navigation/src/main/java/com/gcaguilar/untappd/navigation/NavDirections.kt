package com.gcaguilar.untappd.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object AuthenticationDirections {
    val root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "rootAuthentication"
    }

    val authentication = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "authentication"
    }
}

object SearchDirections {
    val root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "rootSearch"
    }

    val search = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "search"
    }

    object BeerDetailNavigation {
        private const val KEY_BID = "{bid}"
        val argumentList: List<NamedNavArgument> =
            listOf(navArgument("bid") { type = NavType.IntType })
        const val route: String = "beerDetail/$KEY_BID"

        fun beerDetail(
            bid: Int
        ) = object : NavigationCommand {
            override val arguments: List<NamedNavArgument> = argumentList
            override val destination: String = "beerDetail/$bid"
        }
    }
}
