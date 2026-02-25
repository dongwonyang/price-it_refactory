package project.priceit.feature.search.model

sealed interface SearchEvent {
    data class ChangeQuery(val query: String) : SearchEvent
    data class ClickFilterItem(val filterItem: FilterItem) : SearchEvent
    object ClickSearch : SearchEvent
}
sealed interface SearchEffect {
}
