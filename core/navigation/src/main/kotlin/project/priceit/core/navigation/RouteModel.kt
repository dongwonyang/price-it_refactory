package project.priceit.core.navigation

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object AuthRoute : Route

    companion object {
        val entries: List<Route> = listOf(
            AuthRoute,
            MainTabRoute.Home,
            MainTabRoute.Search,
            MainTabRoute.Request,
            MainTabRoute.My,
        )

        fun find(predicate: (Route) -> Boolean): Route? =
            entries.find(predicate)
    }
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Request : MainTabRoute

    @Serializable
    data object My : MainTabRoute
}