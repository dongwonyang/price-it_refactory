package project.priceit.feature.my.model

data class MyUiState(
    val myProfileSectionUiState: MyProfileSectionUiState = MyProfileSectionUiState(),
)

data class MyProfileSectionUiState(
    val userName: String = "",
    val profileImageUrl: String? = null,
    val point: Int = 0
)