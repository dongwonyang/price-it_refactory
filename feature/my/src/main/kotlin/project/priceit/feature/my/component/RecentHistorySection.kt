package project.priceit.feature.my.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.priceit.core.designsystem.component.CommonListItem
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Typography
import project.priceit.feature.my.model.RecentActivityUiState
import project.priceit.model.HistoryType
import project.priceit.util.toRelativeText

@Composable
fun RecentHistorySection(
    uiState: RecentActivityUiState,
    onClick: (HistoryType) -> Unit
) {
    Column() {
        uiState.recentWork.let { recentWork ->
            if (recentWork != null) {
                Text(text = "최근 작업", fontWeight = FontWeight.Bold, style = Typography.titleMedium)

                CommonListItem(
                    mainText = recentWork.title,
                    subText = recentWork.date.toRelativeText(),
                    onClick = { onClick(HistoryType.WORK) },
                )
            }
        }

        Spacer(modifier = Modifier.padding(Dimens.DpSmall))

        uiState.recentRequest.let{ recentRequest ->
            if (recentRequest != null) {
                Text(text = "최근 의뢰", fontWeight = FontWeight.Bold, style = Typography.titleMedium)

                CommonListItem(
                    mainText = recentRequest.title,
                    subText = recentRequest.date.toRelativeText(),
                    onClick = { onClick(HistoryType.REQUEST) },
                )
            }
        }

    }
}

@Composable
@Preview
fun RecentHistorySectionPreview() {
    RecentHistorySection(
        uiState = RecentActivityUiState(),
        onClick = {}
    )
}