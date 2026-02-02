package project.priceit.feature.my.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.theme.Black
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Gray
import project.priceit.core.designsystem.theme.Typography
import project.priceit.feature.my.model.MyProfileSectionUiState

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    myProfileUiState: MyProfileSectionUiState,
    onEditProfileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.DpMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = myProfileUiState.profileImageUrl,
            fallback = painterResource(R.drawable.ic_person),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = Dimens.DpMedium)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = myProfileUiState.userName,
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Point: " + myProfileUiState.point.toString(),
                style = Typography.bodySmall,
                color = Black50
            )
        }

        CommonButton(
            modifier = Modifier
                .height(36.dp)
                .weight(1f),
            text = "프로필 수정",
            onClick = onEditProfileClick,
            bgColor = Gray,
            outlineColor = Gray,
            textColor = Black
        )
    }
}

@Composable
@Preview
fun ProfileSectionPreview() {
    ProfileSection(
        myProfileUiState = MyProfileSectionUiState(
            userName = "홍길동"
        ),
        onEditProfileClick = {},
    )
}