package project.priceit.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary
import project.priceit.core.designsystem.theme.White
import project.priceit.feature.search.component.FilterSection
import project.priceit.feature.search.component.SearchSection
import project.priceit.feature.search.model.SearchEvent
import project.priceit.feature.search.model.SearchUiState

@Composable
internal fun SearchRoute(
    padding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { eff ->
            when (eff) {
                else -> {}
            }
        }
    }

    SearchScreen(
        padding = padding,
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SearchScreen(
    padding: PaddingValues,
    uiState: SearchUiState,
    onEvent: (SearchEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(horizontal = Dimens.CommonPadding, vertical = Dimens.DpMedium)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Dimens.DpMedium)
    ) {
        uiState.searchUiSection.run {
            SearchSection(
                query = query,
                onQueryChange = { onEvent(SearchEvent.ChangeQuery(it)) },
            )

            FilterSection(
                filterList = searchFilterList,
                onFilterItemClick = { filterItem ->
                    onEvent(SearchEvent.ClickFilterItem(filterItem))
                },
            )

            CommonButton(
                modifier = Modifier.fillMaxWidth(),
                text = "검색하기",
                onClick = { onEvent(SearchEvent.ClickSearch) },
                bgColor = White,
                outlineColor = Primary,
                textColor = Primary
            )
        }
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    SearchScreen(
        padding = PaddingValues(0.dp),
        uiState = SearchUiState(),
        onEvent = {}
    )
}