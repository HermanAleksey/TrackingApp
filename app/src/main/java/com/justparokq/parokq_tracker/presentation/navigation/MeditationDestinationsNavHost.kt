package com.justparokq.parokq_tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.justparokq.feature.splash_screen.api.SplashScreenViewModel
import com.justparokq.parokq_tracker.presentation.screen.NavGraphs
import com.justparokq.parokq_tracker.presentation.screen.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MeditationDestinationsNavHost(
    navController: NavHostController,
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController,
        engine = MeditationNavHostEngine(),
        dependenciesContainerBuilder = {
            dependency(SplashScreenDestination) {
                hiltViewModel<SplashScreenViewModel>()
            }
        }
    )
}
