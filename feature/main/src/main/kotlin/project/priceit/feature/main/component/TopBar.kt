package project.priceit.feature.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.theme.Typography
import project.priceit.core.navigation.MainTabRoute
import project.priceit.core.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    route: Route?,
    onBackClick: () -> Unit,
    onBellClick: () -> Unit,
) {
    if (route != null && route != Route.AuthRoute) {
        TopAppBar(
            title = {
                if (route == MainTabRoute.Home) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_price_it_no_text),
                            contentDescription = "PRICE-IT 로고",
                            modifier = Modifier
                                .height(32.dp)
                                .width(120.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                } else {
                    Text(
                        text = route.toString(),
                        color = Color.Black,
                        style = Typography.titleMedium
                    )
                }

            },
            navigationIcon = {
                if(route !is MainTabRoute)
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_left),
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                if (route is MainTabRoute) {
                    IconButton(onClick = {
                        onBellClick()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_bell),
                            contentDescription = "notification"
                        )
                    }
                }
            },
        )
    }
}