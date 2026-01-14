package project.priceit.feature.request

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import project.priceit.feature.request.model.RequestEffect
import project.priceit.feature.request.model.RequestEvent
import project.priceit.feature.request.model.RequestUiState
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<RequestUiState> = MutableStateFlow(RequestUiState())
    val uiState: StateFlow<RequestUiState> = _uiState.asStateFlow()

    private val _effect = Channel<RequestEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: RequestEvent) {
        when (event) {
            else -> {}
        }
    }
}
