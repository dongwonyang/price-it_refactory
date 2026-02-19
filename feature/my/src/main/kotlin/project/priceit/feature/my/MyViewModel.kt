package project.priceit.feature.my

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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
            MyEvent.HideProfileDialog -> setProfileDialog(false)
            MyEvent.ShowProfileDialog -> setProfileDialog(true)
            is MyEvent.SetProfile -> setProfile(event.newNickname, event.newImageUri)
            is MyEvent.ClickHistory -> {
                _effect.trySend(MyEffect.NavigateHistory(event.historyType))
            }
            else -> {}
        }
    }

    fun setProfileDialog(isDialog: Boolean) {
        _uiState.update { prev ->
            prev.copy(myProfileSectionUiState = prev.myProfileSectionUiState.copy(isDialog = isDialog))
        }
    }

    fun setProfile(newNickname: String, newImageUri: Uri?) {
//        TODO() // Implement profile update logic
        setProfileDialog(false)
    }
}
