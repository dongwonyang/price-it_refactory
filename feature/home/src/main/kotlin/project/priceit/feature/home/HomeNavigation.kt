package project.priceit.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import project.priceit.core.navigation.MainTabRoute

fun NavController.navigateHome(
    navOptions: NavOptions = NavOptions.Builder().build()
) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            padding = padding,
        )
    }
}