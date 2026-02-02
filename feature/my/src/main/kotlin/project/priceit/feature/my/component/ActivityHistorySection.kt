package project.priceit.feature.my.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun ActivityHistorySection() {
    val boxList = listOf(
        "작업 기록" to R.drawable.ic_history_work,
        "의뢰 기록" to R.drawable.ic_history_request,
        "포인트 내역" to R.drawable.ic_history_point
    )

    Column() {
        Text(text = "내 활동 내역", fontWeight = FontWeight.Bold, style = Typography.titleMedium)

        Spacer(modifier = Modifier.padding(Dimens.DpSmall))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            boxList.forEach { (text, iconResId) ->
                ActivityBox(
                    text = text,
                    iconResId = iconResId
                )
            }
        }
    }
}

@Composable
fun ActivityBox(
    text:String,
    @DrawableRes iconResId: Int
) {
    val shape = RoundedCornerShape(Dimens.RoundCommon)

    Box(
        modifier = Modifier
            .clip(shape)
            .border(1.dp, color = Gray, shape = shape)
            .padding(vertical = Dimens.DpSmall, horizontal = Dimens.DpLarge)
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
    ActivityHistorySection()
}

@Composable
@Preview
fun ActivityBoxPrev() {
    ActivityBox(
        text = "작업 기록",
        iconResId = R.drawable.ic_history_work
    )
}
