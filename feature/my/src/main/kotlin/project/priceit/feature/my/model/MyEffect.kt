package project.priceit.feature.my.model

import android.net.Uri

sealed interface MyEvent {
    object ShowProfileDialog : MyEvent
    object HideProfileDialog : MyEvent
    data class SetProfile(val newNickname: String, val newImageUri: Uri?) : MyEvent
}

sealed interface MyEffect {
    object NavigateHistory : MyEffect
}
