package project.priceit.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.auth.component.LoginContent
import project.priceit.feature.auth.component.SignupBottomSheet
import project.priceit.feature.auth.model.AuthEffect
import project.priceit.feature.auth.model.AuthEvent
import project.priceit.feature.auth.model.AuthUiState

@Composable
internal fun AuthRoute(
    padding: PaddingValues,
    navigateHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AuthEffect.NavigateHome -> navigateHome()
            }
        }
    }

    AuthScreen(
        padding = padding,
        uiState = uiState.value,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun AuthScreen(
    padding: PaddingValues,
    uiState: AuthUiState,
    onEvent: (AuthEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(Dimens.CommonPadding)
            .fillMaxSize(),
    ) {
        LoginContent(
            onLoginClick = { id, pw -> onEvent(AuthEvent.Login(id, pw)) },
            onSignupClick = { onEvent(AuthEvent.SignupOpen) },
        )
    }

    if (uiState?.isSignUp == true) {
        SignupBottomSheet(
            onDismiss = { onEvent(AuthEvent.SignupDismiss) },
            onSignup = { id, pw, nick ->
                onEvent(AuthEvent.Signup(id, pw, nick))
            },
            signUpUiState = uiState.signUpMsgUiState,
            isValidId = { onEvent(AuthEvent.ValidateId(it)) },
            isValidPw = { onEvent(AuthEvent.ValidatePw(it)) },
            isValidNick = { onEvent(AuthEvent.ValidateNick(it)) },
        )
    }
}

