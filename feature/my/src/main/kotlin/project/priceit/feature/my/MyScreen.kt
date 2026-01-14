package project.priceit.feature.my

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.feature.my.model.MyEvent
import project.priceit.feature.my.model.MyUiState

@Composable
internal fun MyRoute(
    padding: PaddingValues,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { eff ->
            when (eff) {
                else -> {}
            }
        }
    }

    MyScreen(
        padding = padding,
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun MyScreen(
    padding: PaddingValues,
    uiState: MyUiState,
    onEvent: (MyEvent) -> Unit
) {

}