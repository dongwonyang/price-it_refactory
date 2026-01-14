package project.priceit.feature.my

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import project.priceit.core.navigation.MainTabRoute

fun NavController.navigateMy(
    navOptions: NavOptions = NavOptions.Builder().build()
) {
    navigate(MainTabRoute.My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.My> {
        MyRoute(
            padding = padding,
        )
    }
}