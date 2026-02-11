package project.priceit.feature.my.model

data class MyUiState(
    val myProfileSectionUiState: MyProfileSectionUiState = MyProfileSectionUiState(),
)

data class MyProfileSectionUiState(
    val userName: String = "tester",
    val profileImageUrl: String? = null,
    val point: Int = 0,
    val isDialog: Boolean = false
)