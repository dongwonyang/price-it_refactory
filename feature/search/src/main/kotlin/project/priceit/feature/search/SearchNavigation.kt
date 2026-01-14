package project.priceit.feature.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import project.priceit.core.navigation.MainTabRoute

fun NavController.navigateSearch(
    navOptions: NavOptions = NavOptions.Builder().build()
) {
    navigate(MainTabRoute.Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Search> {
        SearchRoute(
            padding = padding,
        )
    }
}