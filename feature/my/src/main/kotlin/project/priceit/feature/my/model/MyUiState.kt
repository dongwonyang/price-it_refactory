package project.priceit.feature.my.model

import java.time.LocalDateTime

data class MyUiState(
    val myProfileSectionUiState: MyProfileSectionUiState = MyProfileSectionUiState(),
    val recentActivityUiState: RecentActivityUiState = RecentActivityUiState()
)

data class MyProfileSectionUiState(
    val userName: String = "tester",
    val profileImageUrl: String? = null,
    val point: Int = 0,
    val isDialog: Boolean = false
)

data class RecentActivityUiState(
    val recentWork: RecentActivity = RecentActivity(
        title = "최근 작업",
        date = LocalDateTime.now().minusDays(4)
    ),
    val recentRequest: RecentActivity = RecentActivity(
        title = "최근 의뢰",
        date = LocalDateTime.now().minusDays(24)
    )
)

data class RecentActivity(
    val title: String,
    val date: LocalDateTime
)