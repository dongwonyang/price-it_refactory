package project.priceit.feature.my.model

import android.net.Uri
import project.priceit.model.HistoryType

sealed interface MyEvent {
    object ShowProfileDialog : MyEvent
    object HideProfileDialog : MyEvent
    data class SetProfile(val newNickname: String, val newImageUri: Uri?) : MyEvent

    data class ClickHistory(val historyType: HistoryType) : MyEvent
}

sealed interface MyEffect {
    data class NavigateHistory(val historyType: HistoryType) : MyEffect
}
