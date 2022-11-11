package com.gcaguilar.untappd

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.gcaguilar.untappd.navigation.NavigationCommand
import com.gcaguilar.untappd.navigation.SearchDirections

data class HomeNavigationItem(val title: String, val icon: ImageVector?, val navigationCommand: NavigationCommand)

private val homeNavigationItems = listOf(
    HomeNavigationItem("Search", Icons.Sharp.Search, SearchDirections.root)
)

@Composable
fun HomeBottomNavigation(
    selectedNavigation: String,
    onNavigationSelected: (NavigationCommand) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        homeNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon!!, contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = selectedNavigation == item.navigationCommand.destination,
                onClick = { onNavigationSelected(item.navigationCommand) },
            )
        }
    }
}