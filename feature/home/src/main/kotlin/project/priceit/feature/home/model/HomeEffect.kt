package project.priceit.feature.home.model

sealed interface HomeEvent {
    // 지도 반경 설정 다이얼로그 표시/숨김
    object ShowRadiusDialog : HomeEvent
    object HideRadiusDialog : HomeEvent

    // 반경 설정 값 변경
    data class RadiusTempChanged(val radius: Float) : HomeEvent
}
sealed interface HomeEffect {
    object NavigateMy : HomeEffect
}
