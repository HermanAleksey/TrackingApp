//package com.justparokq.parokq_tracker.presentation.navigation
//
//import androidx.navigation.NavHostController
//import com.justparokq.core.common.navigation.NavDependencies
//import com.justparokq.core.common.navigation.NavDependenciesProvider
//import com.justparokq.feature.authentication.api.enter_screen.EnterScreenNavDependencies
//import com.justparokq.mediose.presentation.screens.destinations.*
//
//class NavDependenciesProviderImpl(
//    private val navController: NavHostController,
//) : NavDependenciesProvider {
//
//    override fun <D : NavDependencies> provideDependencies(clazz: Class<D>): D {
//        return findAuthFlowNavDependencies(clazz)
//            ?: throw NotImplementedError(
//                "NavDependencies not implemented for class ${clazz.canonicalName}"
//            )
//    }
//
//    private fun <D : NavDependencies> findAuthFlowNavDependencies(clazz: Class<D>): D? {
//        val dependencies = when (clazz.name) {
//            //todo to update
//            EnterScreenNavDependencies::class.java.name -> {
//                EnterScreenNavDependencies(
//                    navigateToLoginScreen = {
////                        navController.navigate(LoginScreenDestination())
//                    },
//                )
//            }
//            else -> null
//        }
//        @Suppress("UNCHECKED_CAST")
//        return dependencies as D?
//    }
//}
