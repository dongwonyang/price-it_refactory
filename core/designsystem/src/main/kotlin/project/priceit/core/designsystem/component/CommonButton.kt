package project.priceit.core.designsystem.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(Dimens.ButtonHeight),
        colors = ButtonDefaults.buttonColors(containerColor = Primary),
        shape = RoundedCornerShape(Dimens.RoundCommon),
        enabled = enabled
    ) {
        Text(text, color = White)
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