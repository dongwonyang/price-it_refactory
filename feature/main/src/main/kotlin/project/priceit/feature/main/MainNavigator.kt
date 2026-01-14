package project.priceit.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import project.priceit.core.navigation.MainTabRoute
import project.priceit.core.navigation.Route
import project.priceit.feature.home.navigateHome
import project.priceit.feature.my.navigateMy
import project.priceit.feature.request.navigateRequest
import project.priceit.feature.search.navigateSearch

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination: Route = Route.AuthRoute

    private val singleTopOptions = navOptions {
        launchSingleTop = false
        restoreState = true
    }

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    val currentRoute: Route?
        @Composable get() {
            val dest = currentDestination
            return Route.find { route ->
                dest?.hasRoute(route::class) == true
            }
        }



    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true // 나중에 다시 해당 탭으로 돌아올 때 복원 가능
                inclusive = false // popUpTo 대상(startDestination)을 제거할지 여부, false = startDestination은 남김
            }
            launchSingleTop = true // (탭 여러 번 눌러도 중복 쌓임 방지)
            restoreState = true // 다시 해당 탭으로 돌아올 때 UI 상태 유지
        }

        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.MY -> navController.navigateMy(navOptions)
            MainTab.Search ->  navController.navigateSearch(navOptions)
            MainTab.Request -> navController.navigateRequest(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }


    fun navigateHome() {
        navController.navigateHome(singleTopOptions)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}