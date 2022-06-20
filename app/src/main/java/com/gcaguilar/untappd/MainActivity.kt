package com.gcaguilar.untappd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gcaguilar.common_ui.theme.UntappdTheme
import com.gcaguilar.untappd.navigation.Navigation
import com.gcaguilar.untappd.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UntappdTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            content = {
                                title = "Home"
                            }
                        )
                    },
                    content = { paddingValues ->
                        Navigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            navController = navController
                        )
                    },
                    bottomBar = {
                        HomeBottomNavigation(
                            modifier = Modifier,
                            onNavigationSelected = {
                                navController.navigate(it)
                            },
                            selectedNavigation = Screen.Search
                        )
                    }
                )
            }
        }
    }
}