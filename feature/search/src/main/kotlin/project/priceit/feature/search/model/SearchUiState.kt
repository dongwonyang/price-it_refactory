package project.priceit.feature.search.model

data class SearchUiState (
    val searchScreenType: SearchScreenType = SearchScreenType.DEFAULT,
    val userName: String = "",
    val searchUiSection: SearchUiSection = SearchUiSection()
)

data class SearchUiSection(
    val query: String = "",
    val searchFilterList: List<FilterItem> = listOf(
        FilterItem(name = "Region 1", isSelected = true),
        FilterItem(name = "Region 2", isSelected = false),
        FilterItem(name = "Region 3", isSelected = false),
    )
)

