package project.priceit.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import project.priceit.feature.auth.authNavGraph
import project.priceit.feature.history.historyNavGraph
import project.priceit.feature.home.homeNavGraph
import project.priceit.feature.main.MainNavigator
import project.priceit.feature.my.myNavGraph
import project.priceit.feature.request.requestNavGraph
import project.priceit.feature.search.searchNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            authNavGraph(
                padding = padding,
                navigateHome = navigator::navigateHome
            )

            homeNavGraph(
                padding = padding,
            )

            searchNavGraph(
                padding = padding,
            )

            requestNavGraph(
                padding = padding,
            )

            myNavGraph(
                padding = padding,
                navigateHistory = navigator::navigateHistory
            )

            historyNavGraph(
                padding = padding,
            )
        }
    }
}