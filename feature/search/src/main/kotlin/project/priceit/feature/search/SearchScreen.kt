package project.priceit.feature.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

}