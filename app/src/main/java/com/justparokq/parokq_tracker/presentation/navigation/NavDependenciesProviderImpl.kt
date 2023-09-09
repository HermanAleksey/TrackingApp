package com.justparokq.parokq_tracker.presentation.navigation

import androidx.navigation.NavHostController
import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavDependenciesProvider
import com.justparokq.feature.splash_screen.api.SplashScreenNavDependencies
import com.justparokq.parokq_tracker.presentation.screen.destinations.ActivityConfigurationScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class NavDependenciesProviderImpl(
    private val navController: NavHostController,
) : NavDependenciesProvider {

    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
        return findAuthFlowNavDependencies(clazz)
            ?: throw NotImplementedError(
                "NavDependencies not implemented for class ${clazz.canonicalName}"
            )
    }

    private fun <D : NavDependencies> findAuthFlowNavDependencies(clazz: Class<D>): D? {
        val dependencies = when (clazz.name) {
            SplashScreenNavDependencies::class.java.name -> {
                SplashScreenNavDependencies(
                    navigateToEnterScreen = {
//                        navController.navigate(LoginScreenDestination())
                    },
                    navigateToMainScreen = {
                        navController.navigate(ActivityConfigurationScreenDestination())
                    }
                )
            }
            else -> null
        }
        @Suppress("UNCHECKED_CAST")
        return dependencies as D?
    }
}
