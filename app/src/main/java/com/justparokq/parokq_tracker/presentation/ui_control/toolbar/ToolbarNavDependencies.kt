package com.justparokq.parokq_tracker.presentation.ui_control.toolbar

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class ToolbarNavDependencies(
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class ToolbarNavRoute : NavRoute<ToolbarNavDependencies>() {

    object EnterScreen : ToolbarNavRoute() {
        override fun navigate(navDependencies: ToolbarNavDependencies) {
            navDependencies.navigateToEnterScreen()
        }
    }
}
