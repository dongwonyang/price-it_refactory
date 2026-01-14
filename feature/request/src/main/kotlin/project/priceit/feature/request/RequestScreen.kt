package project.priceit.feature.request

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.feature.request.model.RequestEvent
import project.priceit.feature.request.model.RequestUiState

@Composable
internal fun RequestRoute(
    padding: PaddingValues,
    viewModel: RequestViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { eff ->
            when (eff) {
                else -> {}
            }
        }
    }

    RequestScreen(
        padding = padding,
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RequestScreen(
    padding: PaddingValues,
    uiState: RequestUiState,
    onEvent: (RequestEvent) -> Unit
) {

}