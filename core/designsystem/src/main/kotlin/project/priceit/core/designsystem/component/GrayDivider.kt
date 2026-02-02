package project.priceit.core.designsystem.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.theme.Gray

@Composable
fun GrayDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        color = Gray,
        thickness = 1.dp,
        modifier = modifier
    )
}
