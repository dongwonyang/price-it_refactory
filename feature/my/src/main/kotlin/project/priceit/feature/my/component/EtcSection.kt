package project.priceit.feature.my.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.my.model.MyEvent

@Composable
fun EtcSection(
    onEvent: (MyEvent) -> Unit
) {
    val list = listOf(
        "알람 설정" to R.drawable.ic_set,
        "신고하기" to R.drawable.ic_report,
        "고객 지원" to R.drawable.ic_support,
        "로그아웃" to R.drawable.ic_out,
        "탈퇴하기" to R.drawable.img_withdraw,
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.DpMedium)
    ) {
        list.forEach { (label, iconRes) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(Dimens.DpSmall))

                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_left),
                    contentDescription = null,
                    modifier = Modifier.scale(scaleX = -1f, scaleY = 1f)
                )
            }
        }
    }
}


@Composable
@Preview
fun EtcSectionPreview() {
    EtcSection(
        onEvent = {}
    )
}