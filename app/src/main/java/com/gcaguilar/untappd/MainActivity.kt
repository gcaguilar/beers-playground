package com.gcaguilar.untappd

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gcaguilar.authentication.AuthenticationScreen
import com.gcaguilar.beer.search_beer.presentation.FilterScreen
import com.gcaguilar.beer.search_beer.presentation.GenericFilterScreen
import com.gcaguilar.beer_detail.presentation.BeerDetailScreen
import com.gcaguilar.common_ui.theme.AppTheme
import com.gcaguilar.untappd.navigation.AuthenticationDirections
import com.gcaguilar.untappd.navigation.NavigationCommand
import com.gcaguilar.untappd.navigation.NavigationManager
import com.gcaguilar.untappd.navigation.SearchDirections
import com.gcaguilar.beer.search_beer.presentation.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContent {
            AppTheme {
                val state = viewModel.state.collectAsState()
                splashScreen.setKeepOnScreenCondition { !state.value.isLoading }

                AppNavigation(
                    navigationManager = navigationManager,
                    onNavigationItemSelected = {
                        viewModel.onNavigationItemSelected(it)
                    }
                )

                LaunchedEffect(Unit) {
                    viewModel.isLoggedIn()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationManager: NavigationManager,
    onNavigationItemSelected: (NavigationCommand) -> Unit,
) {
    var currentRoute by remember { mutableStateOf("") }

    navigationManager.commands.collectAsState().value.also { command ->
        Log.d("Interceptor", "Collect ${command.destination}")
        if (command.destination.isNotEmpty()) {
            Log.d("Interceptor", "Not empty ${command.destination}")
            LaunchedEffect(command.destination) {
                Log.d("Interceptor", "Launch ${command.destination}")
                navController.navigate(command.destination)
                currentRoute = command.destination
            }
        }
    }
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = AuthenticationDirections.root.destination,
    ) {
        addAuthentication()
        addSearch(currentRoute) {
            onNavigationItemSelected(it)
        }
    }
}

fun NavGraphBuilder.addSearch(
    currentRoute: String,
    onNavigationItemSelected: (NavigationCommand) -> Unit
) {
    navigation(
        startDestination = SearchDirections.search.destination,
        route = SearchDirections.root.destination,
    ) {
        composable(
            route = SearchDirections.search.destination
        ) {
            SearchScreen(
                bottomNavigation = {
                    HomeBottomNavigation(
                        selectedNavigation = currentRoute,
                        onNavigationSelected = {
                            onNavigationItemSelected(it)
                        }
                    )
                }
            )
        }
        composable(
            route = SearchDirections.BeerDetailNavigation.route,
            arguments = SearchDirections.BeerDetailNavigation.argumentList
        ) { backStackEntry ->
            BeerDetailScreen(bid = backStackEntry.arguments?.getInt("bid")!!)
        }
        composable(
            route = SearchDirections.filters.destination
        ) { backStackEntry ->
            FilterScreen()
        }
    }
}

fun NavGraphBuilder.addAuthentication() {
    navigation(
        startDestination = AuthenticationDirections.authentication.destination,
        route = AuthenticationDirections.root.destination
    ) {
        composable(AuthenticationDirections.authentication.destination) {
            AuthenticationScreen()
        }
    }
}
