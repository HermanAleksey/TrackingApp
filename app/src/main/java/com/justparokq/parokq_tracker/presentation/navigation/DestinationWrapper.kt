//package com.justparokq.parokq_tracker.presentation.navigation
//
//import androidx.navigation.NavDestination
//import com.justparokq.mediose.presentation.screens.destinations.EnterScreenDestination
//import com.justparokq.mediose.presentation.screens.destinations.NoInternetConnectionScreenDestination
//import com.justparokq.mediose.presentation.screens.destinations.TypedDestination
//
//enum class DestinationWrapper(
//    val destination: TypedDestination<*>,
//    val toolbarVisible: Boolean = false,
//    val bottomBarVisible: Boolean = false,
//) {
//    NoInternetConnectionScreen(NoInternetConnectionScreenDestination),
//
//    EnterScreen(EnterScreenDestination),
//}
//
//fun NavDestination.getDestinationWrapper() =
//    DestinationWrapper.values().find {
//        this.route?.startsWith(it.destination.route) ?: false
//    }
