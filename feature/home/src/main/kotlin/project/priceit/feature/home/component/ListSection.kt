package project.priceit.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import project.priceit.core.designsystem.component.CommonListItem
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Typography
import project.priceit.feature.home.model.RequestItem

@Composable
fun ListSection(
    title:String,
    items: List<RequestItem>
) {
    Text(title, fontWeight = FontWeight.Bold, style = Typography.bodyMedium)
    Spacer(modifier = Modifier.height(Dimens.DpSmall))

    Column {
        items.forEach { item ->
            item.run {
                CommonListItem(
                    mainText = title,
                    subText = location,
                    rightText = reward,
                    onClick = {  }
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = Dimens.DpSmall))
        }
    }
}