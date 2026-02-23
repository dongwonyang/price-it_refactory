package project.priceit.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary

@Composable
fun CommonListItem(
    modifier: Modifier = Modifier,
    mainText: String,
    subText: String,
    rightText: String = "",
    @DrawableRes mainIcon: Int? = null,
    iconTint: Color? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.DpSmall)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp) // 전체 원 크기
                .background(
                    color = Color(0xFFF0F0F0), // 연한 회색 배경
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = if (mainIcon == null) {
                    painterResource(id = R.drawable.ic_place) // 기본 아이콘
                } else {
                    painterResource(id = mainIcon)
                },
                contentDescription = "위치 아이콘",
                tint = iconTint ?: Primary,
                modifier = Modifier.size(32.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = mainText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
                Text(
                    text = subText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Black50,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
        }

        Text(
            text = rightText,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun CommonListItemPreview() {
    CommonListItem(
        mainText = "서울특별시 강남구 테헤란로 123",
        subText = "강남역 인근",
        rightText = "2.5km",
        onClick = {}
    )
}