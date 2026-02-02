package project.priceit.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Gray

@Composable
fun ShadowDivider(
    modifier: Modifier = Modifier,
    lineColor: Color = Gray,
    shadowColor: Color = Gray,
    shadowHeight: Dp = 4.dp
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // 얇은 라인
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(lineColor)
        )

        // 아래로 퍼지는 그라디언트 그림자
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(shadowHeight)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(shadowColor, Color.Transparent)
                    )
                )
        )
    }
}

@Preview
@Composable
fun ShadowDividerPreview() {
    ShadowDivider()
}