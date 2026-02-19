package project.priceit.feature.my.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Gray
import project.priceit.core.designsystem.theme.Typography
import project.priceit.model.HistoryType

@Composable
fun ActivityHistorySection(
    onClickBox: (HistoryType) -> Unit
) {
    val boxList = listOf(
        Triple("작업 기록", R.drawable.ic_history_work, HistoryType.WORK),
        Triple("의뢰 기록", R.drawable.ic_history_request, HistoryType.REQUEST),
        Triple("포인트 내역", R.drawable.ic_history_point, HistoryType.POINT)
    )

    Column() {
        Text(text = "내 활동 내역", fontWeight = FontWeight.Bold, style = Typography.titleMedium)

        Spacer(modifier = Modifier.padding(Dimens.DpSmall))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            boxList.forEach { (text, iconResId, historyType) ->
                ActivityBox(
                    text = text,
                    iconResId = iconResId,
                    onClick = { onClickBox(historyType) }
                )
            }
        }
    }
}

@Composable
fun ActivityBox(
    text: String,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(Dimens.RoundCommon)

    Box(
        modifier = Modifier
            .clip(shape)
            .border(1.dp, color = Gray, shape = shape)
            .padding(vertical = Dimens.DpSmall, horizontal = Dimens.DpLarge)
            .clickable(onClick = onClick)
    ) {
        Column() {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null
            )

            Spacer(modifier = Modifier.padding(Dimens.DpSmall))

            Text(
                text = text,
                style = Typography.bodySmall
            )
        }
    }
}

@Composable
@Preview
fun ActivityHistorySectionPrev() {
    ActivityHistorySection(
        onClickBox = {}
    )
}

@Composable
@Preview
fun ActivityBoxPrev() {
    ActivityBox(
        text = "작업 기록",
        iconResId = R.drawable.ic_history_work,
        onClick = {}
    )
}
