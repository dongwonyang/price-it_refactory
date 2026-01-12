package project.priceit.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary
import project.priceit.core.designsystem.theme.Typography

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    bgColor: Color = Primary,
    outlineColor: Color = Primary,
    textColor: Color = White
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(Dimens.ButtonHeight),
        border = BorderStroke(if (enabled) 1.dp else 0.dp, outlineColor),
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        shape = RoundedCornerShape(Dimens.RoundCommon),
        enabled = enabled
    ) {
        Text(text, color = textColor, style = Typography.bodyMedium)
    }
}

@Preview
@Composable
fun CommonButtonPreview() {
    CommonButton(
        text = "공통 버튼",
        onClick = {}
    )
}