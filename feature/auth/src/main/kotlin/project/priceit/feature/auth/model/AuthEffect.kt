package project.priceit.feature.auth.model

sealed interface AuthEvent {
    // Login
    data class Login(val id: String, val pw: String) : AuthEvent

    // Signup modal
    object SignupOpen : AuthEvent
    object SignupDismiss : AuthEvent

    // Signup validations
    data class ValidateId(val id: String) : AuthEvent
    data class ValidatePw(val pw: String) : AuthEvent
    data class ValidateNick(val nick: String) : AuthEvent

    // 회원가입 요청
    data class Signup(val id: String, val pw: String, val nick: String) : AuthEvent
}

sealed interface AuthEffect {
    object NavigateHome : AuthEffect
}