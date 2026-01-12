package project.priceit.feature.auth.model

data class AuthUiState (
        val isSignUp: Boolean = false,
        val alertMsg: String = "",
        val signUpMsgUiState: SignUpUiState = SignUpUiState()
)

data class SignUpUiState(
    val idMsg: ResponseMsg = ResponseMsg(),
    val pwMsg: ResponseMsg = ResponseMsg(),
    val nicknameMsg: ResponseMsg = ResponseMsg(),
)

data class ResponseMsg(
    val message: String = "",
    val errorState: Boolean? = null// true 일경우 에러, 붉은 msg 표시, null일 경우 검사 안함
)