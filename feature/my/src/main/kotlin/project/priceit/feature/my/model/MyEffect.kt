package project.priceit.feature.my.model

sealed interface MyEvent {
}
sealed interface MyEffect {
    object NavigateHistory : MyEffect
}
