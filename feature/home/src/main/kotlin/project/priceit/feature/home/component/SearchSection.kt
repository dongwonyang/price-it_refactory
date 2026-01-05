package project.priceit.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.component.CommonEditTextBox


@Composable
fun SearchSection(
    onSearchClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column() {
        Text(
            text = "장소 검색",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(6.dp))

        CommonEditTextBox(
            value = query,
            onValueChange = { query = it },
            placeHolder = "장소를 입력하세요.",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))


        CommonButton(
            text = "검색",
            onClick = { onSearchClick(query) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun SearchSectionPreview() {
    SearchSection(
        onSearchClick = {}
    )
}