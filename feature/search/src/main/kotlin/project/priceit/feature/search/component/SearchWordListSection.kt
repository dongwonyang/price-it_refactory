package project.priceit.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.component.GrayDivider
import project.priceit.core.designsystem.component.NoSubStringListItem
import project.priceit.core.designsystem.theme.Dimens

@Composable
fun SearchWordListSection(
    lable: String,
    wordList: List<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.DpMedium)
    ) {
        Text(
            text = lable,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        wordList.forEach { word ->
            NoSubStringListItem(
                iconRes = R.drawable.ic_search,
                label = word
            )
            GrayDivider()
        }
    }
}

@Composable
@Preview
fun SearchWordListSectionPreview() {
    SearchWordListSection(
        lable = "최근 검색어",
        wordList = listOf("서울시청", "강남역", "홍대입구역")
    )
}