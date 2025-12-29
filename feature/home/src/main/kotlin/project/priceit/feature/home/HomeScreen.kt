package project.priceit.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.home.component.MapSection
import project.priceit.feature.home.component.RadiusSettingDialog

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    navigateMy: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        uiState = uiState.value,
        viewModel = viewModel
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    uiState: HomeUiState,
    viewModel: HomeViewModel
) {
    val sampleRequests = listOf(
        RequestItem("딸기 한 팩 가격", "수석 마트", "리워드 : 15p"),
        RequestItem("진라면 한 묶음 가격", "상암 홈플러스", "리워드 : 10p")
    )

    //스크롤 상태
    val scrollState = rememberScrollState()
    // 'true'면 스크롤 잠금 → 맵만 드래그 가능
    var isMapTouched by remember { mutableStateOf(false) }

    when (uiState) {
        is HomeUiState.Error -> {}
        HomeUiState.Loading -> {}
        is HomeUiState.Success -> {
            if (uiState.isRadiusDialogVisible) {
                var tempRadius by remember { mutableStateOf(uiState.searchRadius) }
                RadiusSettingDialog(
                    currentRadius = tempRadius,
                    onRadiusChange = { tempRadius = it },
                    onDismiss = viewModel::hideRadiusDialog,
                    onConfirm = {
                        viewModel.updateSearchRadius(tempRadius)
                        viewModel.hideRadiusDialog()
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding)
                    .padding(Dimens.common)
                    .verticalScroll(scrollState, enabled = !isMapTouched)
            ) {
                MapSection(
                    state = uiState,
                    onMartClick = {},
                    onShowRadiusDialog = viewModel::showRadiusDialog,
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Search section
                var query by remember { mutableStateOf(TextFieldValue("")) }
                Text(text = "장소 검색", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("여기에 장소를 입력하세요") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "현 위치 기반 검색",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* 검색 동작 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
                ) {
                    Text("검색", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Current requests
                Text("현재 진행 중인 의뢰", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                RequestList(items = sampleRequests)

                Spacer(modifier = Modifier.height(12.dp))

                Text("추천 의뢰", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                RequestList(items = sampleRequests)
            }
        }
    }

}

@Composable
fun RequestList(items: List<RequestItem>) {
    Column {
        items.forEach { item ->
            RequestRow(item)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun RequestRow(item: RequestItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* 이동 */ }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3F2FD)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Place, contentDescription = null, tint = Color(0xFF1976D2))
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(item.title, fontWeight = FontWeight.SemiBold)
            Text(item.location, color = Color.Gray, fontSize = 12.sp)
        }

        Text(item.reward, color = Color.Gray, modifier = Modifier.padding(start = 8.dp))
    }
}

data class RequestItem(val title: String, val location: String, val reward: String)

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        padding = PaddingValues(0.dp),
        uiState = HomeUiState.Success(),
        viewModel = null!!
    )
}