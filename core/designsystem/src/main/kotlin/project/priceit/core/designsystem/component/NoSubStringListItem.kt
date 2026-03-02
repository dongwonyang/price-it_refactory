package project.priceit.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun NoSubStringListItem(
    @DrawableRes iconRes: Int,
    rightArrow: Boolean = false,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(Dimens.DpSmall))

        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.weight(1f))

        if(rightArrow) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left),
                contentDescription = null,
                modifier = Modifier.scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }
}

@Composable
@Preview
fun NoSubStringListItemPreview() {
    NoSubStringListItem(
        iconRes = R.drawable.ic_place,
        label = "서울시 강남구 역삼동"
    )
}