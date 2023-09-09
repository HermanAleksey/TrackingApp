package com.justparokq.parokq_tracker.presentation.ui_control.bottom_nav_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.justparokq.core.common.utils.UiText
import com.justparokq.parokq_tracker.presentation.screen.example.NavGraphs
import com.justparokq.parokq_tracker.presentation.screen.example.appCurrentDestinationAsState
import com.justparokq.parokq_tracker.presentation.screen.example.destinations.TypedDestination
import com.justparokq.parokq_tracker.presentation.screen.example.startAppDestination

import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestinations(
    val direction: DirectionDestinationSpec,
    val description: UiText,
    val icon: ImageVector,
) {
//    Main(
//        direction = MainScreenDestination,
//        description = UiText.DynamicString("TODO ADD"),
//        icon = Icons.Default.Home
//    ),
}

@Composable
fun NavigationBottomBar(navController: NavController) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        BottomBarDestinations.values().forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarDestinations,
    currentDestination: TypedDestination<*>?,
    navController: NavController,
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.description.asString(),
                modifier = Modifier.size(26.dp)
            )
        },
        selected = currentDestination == screen.direction,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            if (navController.currentDestination?.route == screen.direction.route)
                return@BottomNavigationItem

            with(navController) {
                navigate(screen.direction) {
//                    popUpTo(MainScreenDestination.route) {
//                        saveState = true
//                        inclusive = false
//                    }
                }
            }
        }
    )
}
