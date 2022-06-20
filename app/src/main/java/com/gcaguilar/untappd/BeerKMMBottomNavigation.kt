package com.gcaguilar.untappd

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.gcaguilar.untappd.navigation.Screen

data class HomeNavigationItem(val title: String, val icon: ImageVector?, val route: String)

private val homeNavigationItems = listOf(
    HomeNavigationItem("Search", Icons.Sharp.Search, "search")
)

@Composable
fun HomeBottomNavigation(
    selectedNavigation: Screen,
    onNavigationSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = contentColorFor(MaterialTheme.colors.surface),
        modifier = modifier
    ) {
        homeNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.icon!!, contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = selectedNavigation.route == item.route,
                onClick = { onNavigationSelected(item.route) },
            )
        }
    }
}