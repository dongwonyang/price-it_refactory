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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.theme.Black5
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.search.model.FilterItem

@Composable
fun FilterSection(
    filterList: List<FilterItem>,
    onFilterItemClick: (FilterItem) -> Unit
) {
    Column {
        Text(
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            text = "filter"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(filterList.isEmpty()){
                FilterItem(
                    filter = "ALL",
                    isSelected = true,
                    onClick = { }
                )
            }
            else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.DpSmall)
                ) {
                    items(filterList) {
                        FilterItem(
                            filter = it.name,
                            isSelected = it.isSelected,
                            onClick = { onFilterItemClick(it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_left),
                contentDescription = "filter arrow",
                modifier = Modifier
                    .size(24.dp)
                    .scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }
}


@Composable
fun FilterItem(
    filter: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Black50 else Black5
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimens.RoundCommon))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = Dimens.DpSmall)
    ) {
        Text(
            text = filter,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
@Preview
fun FilterSectionPreview() {
    FilterSection(
        filterList = listOf(
            FilterItem(name = "Region 1", isSelected = true),
            FilterItem(name = "Region 2", isSelected = false),
            FilterItem(name = "Region 3", isSelected = false),
        ),
        onFilterItemClick = {}
    )
}

@Composable
@Preview
fun FilterItemPreview() {
    FilterItem(
        filter = "Region 1",
        isSelected = true,
        onClick = {}
    )
}