package project.priceit.feature.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import project.priceit.feature.search.model.FilterItem
import project.priceit.feature.search.model.SearchEffect
import project.priceit.feature.search.model.SearchEvent
import project.priceit.feature.search.model.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _effect = Channel<SearchEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ChangeQuery -> {
                _uiState.update { prev->
                    prev.copy(searchUiSection = prev.searchUiSection.copy(query = event.query))
                }
            }
            is SearchEvent.ClickFilterItem -> {
                clickFilterItem(event.filterItem)
            }
            SearchEvent.ClickSearch -> {

            }
            else -> {}
        }
    }

    fun clickFilterItem(filterItem: FilterItem) {
        _uiState.update { prev ->
            val newList = prev.searchUiSection.searchFilterList.map {
                if (it == filterItem) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            }

            prev.copy(
                searchUiSection = prev.searchUiSection.copy(
                    searchFilterList = newList
                )
            )
        }
    }

}
