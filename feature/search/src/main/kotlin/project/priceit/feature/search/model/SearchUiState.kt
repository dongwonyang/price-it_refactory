package project.priceit.feature.search.model

data class SearchUiState (
    val searchScreenType: SearchScreenType = SearchScreenType.DEFAULT,
    val userName: String = "",
    val searchUiSection: SearchUiSection = SearchUiSection(),
    val searchWordListSection: SearchWordListSection = SearchWordListSection()
)

data class SearchUiSection(
    val query: String = "",
    val searchFilterList: List<FilterItem> = listOf(
        FilterItem(name = "Region 1", isSelected = true),
        FilterItem(name = "Region 2", isSelected = false),
        FilterItem(name = "Region 3", isSelected = false),
    )
)

data class SearchWordListSection(
    val recentSearchWordList: List<String> = listOf("Recent Search 1", "Recent Search 2", "Recent Search 3"),
    val recommendSearchWordList: List<String> = listOf("Recommended Search 1", "Recommended Search 2", "Recommended Search 3")
)

