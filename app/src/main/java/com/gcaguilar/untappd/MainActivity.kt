package com.gcaguilar.untappd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gcaguilar.authentication.AUTHORIZATION
import com.gcaguilar.authentication.authorizationGraph
import com.gcaguilar.beer.search_beer.presentation.searchGraph
import com.gcaguilar.common_ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContent {
            AppTheme {
                val navController = rememberNavController()

                val state = viewModel.state.collectAsState()
                splashScreen.setKeepOnScreenCondition { !state.value.isLoading }

                Scaffold(
                    bottomBar = {
                        HomeBottomNavigation(navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = AUTHORIZATION,
                        Modifier.padding(innerPadding)
                    ) {
                        authorizationGraph(navController)
                        searchGraph(navController)
                    }
                }
            }
        }
    }
}
