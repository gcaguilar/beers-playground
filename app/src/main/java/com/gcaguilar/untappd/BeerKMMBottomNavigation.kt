package com.gcaguilar.untappd

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gcaguilar.beer.search_beer.presentation.SEARCH

private sealed class Screen(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    object Search : Screen(title = R.string.screen_title_search, icon = Icons.Sharp.Search, route = SEARCH)
    object SearchOne : Screen(title = R.string.screen_title_search, icon = Icons.Sharp.Search, route = SEARCH)
    object SearchTwo : Screen(title = R.string.screen_title_search, icon = Icons.Sharp.Search, route = SEARCH)
    object SearchThree : Screen(title = R.string.screen_title_search, icon = Icons.Sharp.Search, route = SEARCH)
}

private val menuItems = listOf(
    Screen.Search,
    Screen.SearchOne,
    Screen.SearchTwo,
    Screen.SearchThree
)

@Composable
fun HomeBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        menuItems.forEach { menuItem ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = menuItem.icon, contentDescription = stringResource(menuItem.title))
                },
                label = { Text(text = stringResource(menuItem.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == menuItem.route } == true,
                onClick = {
                    navController.navigate(menuItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
