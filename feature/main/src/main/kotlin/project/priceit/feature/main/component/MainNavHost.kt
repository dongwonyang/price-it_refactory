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
import project.priceit.feature.home.homeNavGraph
import project.priceit.feature.main.MainNavigator

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
           homeNavGraph(
               padding = padding,
               navigateMy = {  }
           )

            authNavGraph(
                padding = padding,
                navigateHome = navigator::navigateHome
            )
        }
    }
}