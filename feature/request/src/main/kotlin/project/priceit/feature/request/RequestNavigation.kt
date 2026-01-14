package project.priceit.feature.request

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import project.priceit.core.navigation.MainTabRoute

fun NavController.navigateRequest(
    navOptions: NavOptions = NavOptions.Builder().build()
) {
    navigate(MainTabRoute.Request, navOptions)
}

fun NavGraphBuilder.requestNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Request> {
        RequestRoute(
            padding = padding,
        )
    }
}