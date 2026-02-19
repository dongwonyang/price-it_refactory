package project.priceit.feature.my

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.component.GrayDivider
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.my.component.ActivityHistorySection
import project.priceit.feature.my.component.ProfileDialog
import project.priceit.feature.my.component.ProfileSection
import project.priceit.feature.my.model.MyEffect
import project.priceit.feature.my.model.MyEvent
import project.priceit.feature.my.model.MyUiState
import project.priceit.model.HistoryType

@Composable
internal fun MyRoute(
    padding: PaddingValues,
    viewModel: MyViewModel = hiltViewModel(),
    navigateHistory: (HistoryType) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { eff ->
            when (eff) {
                is MyEffect.NavigateHistory -> navigateHistory(eff.historyType)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
            .padding(Dimens.CommonPadding)
            .verticalScroll(rememberScrollState())
    ) {
        uiState.myProfileSectionUiState.run {
            if (isDialog) {
                ProfileDialog(
                    currentNickname = userName,
                    currentImageUrl = profileImageUrl,
                    onDismiss = { onEvent(MyEvent.HideProfileDialog) },
                    onConfirm = { newNickname, newImageUri ->
                        onEvent(
                            MyEvent.SetProfile(
                                newNickname = newNickname,
                                newImageUri = newImageUri
                            )
                        )
                    }
                )
            }
        }

        ProfileSection(
            myProfileUiState = uiState.myProfileSectionUiState,
            onEditProfileClick = { onEvent(MyEvent.ShowProfileDialog) },
        )

        Spacer(modifier = Modifier.height(Dimens.DpMedium))
        GrayDivider()
        Spacer(modifier = Modifier.height(Dimens.DpMedium))

        ActivityHistorySection(
            onClickBox = { type ->
                onEvent(MyEvent.ClickHistory(type))
            }
        )
    }
}

@Preview
@Composable
fun MyScreenPreview() {
    MyScreen(
        padding = PaddingValues(0.dp),
        uiState = MyUiState(),
        onEvent = {}
    )
}