package project.priceit.core.navigation

import kotlinx.serialization.Serializable
import project.priceit.core.model.HistoryType

sealed interface Route {
    @Serializable
    data object AuthRoute : Route

    @Serializable
    data class HistoryRoute(val historyType: HistoryType): Route

    companion object {
        val entries: List<Route> = listOf(
            AuthRoute,
            HistoryRoute(HistoryType.WORK),
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