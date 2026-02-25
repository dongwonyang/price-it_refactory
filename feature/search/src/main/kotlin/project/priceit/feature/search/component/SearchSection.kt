package project.priceit.feature.search.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.component.CommonEditTextBox
import project.priceit.core.designsystem.theme.Black5
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Primary
import project.priceit.core.designsystem.theme.White

@Suppress("UNUSED_PARAMETER")
@Composable
fun SearchSection(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Column() {
        CommonEditTextBox(
            label = "Search for a product",
            value = query,
            onValueChange = onQueryChange,
        )
        Spacer(modifier = Modifier.padding(Dimens.DpSmall))
    }
}


@Composable
@Preview
fun SearchSectionPreview() {
    SearchSection(
        query = "",
        onQueryChange = {}
    )
}

