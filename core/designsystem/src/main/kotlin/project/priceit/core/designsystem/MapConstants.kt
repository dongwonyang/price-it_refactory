package project.priceit.core.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.priceit.model.Location

object MapConstants {
    const val DEFAULT_ZOOM = 15.0
    val DEFAULT_LOCATION = Location(37.5665, 126.9780) // 서울시청
    val CIRCLE_FILL_COLOR = Color(0x220000FF)
    val CIRCLE_OUTLINE_COLOR = Color(0xFF0000FF)
    val CIRCLE_OUTLINE_WIDTH = 2.dp
    const val MARKER_ZINDEX = 2
    const val CIRCLE_ZINDEX = 1
    const val METERS_PER_KM = 1000.0
    val USER_LOCATION_MARKER_COLOR = Color(0xFF4285F4) // 구글맵 스타일 파란색
    val MART_WITH_COMMISSION_COLOR = Color(0xFF34A853) // 구글맵 스타일 초록색
    val MART_WITHOUT_COMMISSION_COLOR = Color(0xFF9AA0A6) // 연한 회색
}
