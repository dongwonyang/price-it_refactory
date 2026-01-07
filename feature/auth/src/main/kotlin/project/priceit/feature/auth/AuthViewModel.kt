package project.priceit.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.priceit.core.data.repo.AuthRepository
import project.priceit.feature.auth.model.AuthUiState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Login())
    val uiState: StateFlow<AuthUiState> = _uiState


    fun login(id: String, pw: String) = viewModelScope.launch {
        (uiState.value as? AuthUiState.Login)?.let {
            authRepository.login(id, pw).onSuccess {
                if (it) _uiState.update { AuthUiState.OnLogin }
            }.onFailure { }
        }
    }


}