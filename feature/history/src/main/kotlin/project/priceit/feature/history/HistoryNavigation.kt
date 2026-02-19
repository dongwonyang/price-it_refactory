package project.priceit.feature.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import project.priceit.core.navigation.Route
import project.priceit.model.HistoryType

fun NavController.navigateHistory(
    navOptions: NavOptions = NavOptions.Builder().build(),
    historyType: HistoryType
) {
    navigate(Route.HistoryRoute(historyType), navOptions)
}

fun NavGraphBuilder.historyNavGraph(
    padding: PaddingValues,
) {
    composable<Route.HistoryRoute> {
        HistoryRoute(
            padding = padding,
            historyType = it.toRoute<Route.HistoryRoute>().historyType
        )
    }
}