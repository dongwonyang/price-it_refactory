package project.priceit.feature.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import project.priceit.core.navigation.MainTabRoute
import project.priceit.core.navigation.Route

enum class MainTab(
    @DrawableRes
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        iconResId = project.priceit.core.designsystem.R.drawable.ic_home,
        contentDescription = "홈",
        route = MainTabRoute.Home,
    ),
    Search(
        iconResId = project.priceit.core.designsystem.R.drawable.ic_search,
        contentDescription = "검색",
        route = MainTabRoute.Search,
    ),
    Request(
        iconResId = project.priceit.core.designsystem.R.drawable.ic_request,
        contentDescription = "의뢰",
        route = MainTabRoute.Request,
    ),
    MY(
        iconResId = project.priceit.core.designsystem.R.drawable.ic_my,
        contentDescription = "마이",
        route = MainTabRoute.My,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}