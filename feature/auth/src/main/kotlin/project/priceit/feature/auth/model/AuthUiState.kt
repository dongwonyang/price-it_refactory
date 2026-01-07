package project.priceit.feature.auth.model

sealed class AuthUiState {
    data class Login(val isSignUp: Boolean = false, val alertMsg: String = "") : AuthUiState()

    data object OnLogin : AuthUiState()
}