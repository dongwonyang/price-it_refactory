package project.priceit.feature.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary
import project.priceit.core.designsystem.theme.White

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit,
    onSignupClick: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // 화면 크기에 따른 로고 높이 계산 (화면 높이의 15~20%)
    val logoHeight = (screenHeight * 0.18f).coerceAtMost(160.dp).coerceAtLeast(100.dp)

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_price_it_no_text),
            contentDescription = "PRICE-IT 로고",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(logoHeight)
                .fillMaxWidth(0.8f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(Dimens.DpLarge))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            LoginEditTextBox(
                description = "Email",
                text = id,
                onTextChange = { id = it }
            )
            Spacer(modifier = Modifier.height(Dimens.DpSmall))


            LoginEditTextBox(
                description = "Password}",
                text = pw,
                onTextChange = { pw = it }
            )
            Spacer(modifier = Modifier.height(Dimens.DpLarge))

            CommonButton(
                text = "로그인",
                onClick = { onLoginClick(id, pw) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Dimens.DpSmall))
            CommonButton(
                text = "회원가입",
                onClick = { onSignupClick() },
                bgColor = White,
                textColor = Primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PrevLoginContent() {
    LoginContent(
        onLoginClick = { _, _ -> },
        onSignupClick = {},
        modifier = Modifier
    )
}