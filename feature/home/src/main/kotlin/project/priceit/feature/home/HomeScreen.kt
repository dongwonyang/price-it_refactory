package project.priceit.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.home.component.ListSection
import project.priceit.feature.home.component.MapSection
import project.priceit.feature.home.component.RadiusSettingDialog
import project.priceit.feature.home.component.SearchSection

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        uiState = uiState.value,
        viewModel = viewModel
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeUiState,
    viewModel: HomeViewModel
) {
    //스크롤 상태
    val scrollState = rememberScrollState()
    // 'true'면 스크롤 잠금 → 맵만 드래그 가능
    var isMapTouched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(Dimens.CommonPadding)
            .verticalScroll(scrollState, enabled = !isMapTouched)
    ) {
        when (uiState) {
            is HomeUiState.Error -> {}
            HomeUiState.Loading -> {}
            is HomeUiState.Success -> {
                if (uiState.isRadiusDialogVisible) {
                    var tempRadius by remember { mutableStateOf(uiState.searchRadius) }
                    RadiusSettingDialog(
                        currentRadius = tempRadius,
                        onRadiusChange = { tempRadius = it },
                        onDismiss = viewModel::hideRadiusDialog,
                        onConfirm = {
                            viewModel.updateSearchRadius(tempRadius)
                            viewModel.hideRadiusDialog()
                        }
                    )
                }
                MapSection(
                    state = uiState,
                    onMartClick = {},
                    onShowRadiusDialog = viewModel::showRadiusDialog,
                )
                Spacer(modifier = Modifier.height(Dimens.DpMedium))

                SearchSection { }
                Spacer(modifier = Modifier.height(Dimens.DpMedium))

                ListSection(
                    title = "현재 진행 중인 의뢰",
                    items = uiState.currentRequestList,
                )
                Spacer(modifier = Modifier.height(Dimens.DpMedium))

                ListSection(
                    title = "추천의뢰",
                    items = uiState.recommentRequestList,
                )
            }
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        padding = PaddingValues(0.dp),
        uiState = HomeUiState.test(),
        viewModel = null!!
    )
}