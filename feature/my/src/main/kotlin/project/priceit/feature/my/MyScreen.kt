package project.priceit.feature.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.component.GrayDivider
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.my.component.ActivityHistorySection
import project.priceit.feature.my.component.ProfileSection
import project.priceit.feature.my.model.MyEffect
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
                MyEffect.NavigateHistory -> {}
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
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Dimens.DpMedium)
    ) {
        Spacer(modifier = Modifier.height(Dimens.CommonPadding))

        ProfileSection(
            myProfileUiState = uiState.myProfileSectionUiState,
            onEditProfileClick = {  },
        )

        GrayDivider()

        ActivityHistorySection()

        Spacer(modifier = Modifier.height(Dimens.CommonPadding))
    }
}