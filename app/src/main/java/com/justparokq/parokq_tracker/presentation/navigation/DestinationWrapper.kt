package com.justparokq.parokq_tracker.presentation.navigation

import androidx.navigation.NavDestination
import com.justparokq.parokq_tracker.presentation.screen.destinations.ActivityConfigurationScreenDestination
import com.justparokq.parokq_tracker.presentation.screen.destinations.TypedDestination

enum class DestinationWrapper(
    val destination: TypedDestination<*>,
    val toolbarVisible: Boolean = false,
    val bottomBarVisible: Boolean = false,
) {
    ActivityConfigurationScreen(ActivityConfigurationScreenDestination, true, true),
}

fun NavDestination.getDestinationWrapper() =
    DestinationWrapper.values().find {
        this.route?.startsWith(it.destination.route) ?: false
    }
