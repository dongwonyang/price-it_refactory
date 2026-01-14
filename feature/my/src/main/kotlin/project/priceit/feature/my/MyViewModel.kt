package project.priceit.feature.my

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import project.priceit.feature.my.model.MyEffect
import project.priceit.feature.my.model.MyEvent
import project.priceit.feature.my.model.MyUiState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<MyUiState> = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    private val _effect = Channel<MyEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: MyEvent) {
        when (event) {
            else -> {}
        }
    }
}
