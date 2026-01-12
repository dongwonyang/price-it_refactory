package project.priceit.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.priceit.core.data.repo.AuthRepository
import project.priceit.feature.auth.model.AuthEffect
import project.priceit.feature.auth.model.AuthEvent
import project.priceit.feature.auth.model.AuthUiState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _effect = Channel<AuthEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()


    // 로그인
    fun login(id: String, pw: String) = viewModelScope.launch {
        uiState.value.let {
            authRepository.login(id, pw).onSuccess {
                if (it) _effect.send(AuthEffect.NavigateHome)
            }.onFailure { }
        }
    }

    // 회원가입 모달 온오프
    fun setSignUp(state: Boolean) {
        _uiState.update { prev ->
            prev.copy(isSignUp = state) ?: prev
        }
    }

    // 회원가입 유효성 검사
    fun isValidId(id: String) {}
    fun isValidPw(pw: String) {}
    fun isValidNick(nick: String) {}

    // 이벤트 처리
    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> login(event.id, event.pw)

            AuthEvent.SignupOpen -> setSignUp(true)
            AuthEvent.SignupDismiss -> setSignUp(false)

            is AuthEvent.ValidateId -> isValidId(event.id)
            is AuthEvent.ValidatePw -> isValidPw(event.pw)
            is AuthEvent.ValidateNick -> isValidNick(event.nick)

            is AuthEvent.Signup -> {
                // 회원가입 로직
            }
        }
    }
}