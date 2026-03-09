package project.priceit.feature.home.component

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CircleOverlay
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.util.MarkerIcons
import project.priceit.core.designsystem.MapConstants
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.model.MartEntity
import project.priceit.feature.home.model.HomeUiState
import project.priceit.feature.home.model.toLatLng

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapSection(
    state: HomeUiState,
    searchRadius: Float,
    onMartClick: (MartEntity) -> Unit,
    onShowRadiusDialog: () -> Unit = {},
    isMapTouched: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val currentLocation =
        state.currentLocation?.toLatLng() ?: MapConstants.DEFAULT_LOCATION.toLatLng()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(currentLocation, MapConstants.DEFAULT_ZOOM)
    }

    LaunchedEffect(currentLocation) {
        cameraPositionState.position = CameraPosition(
            currentLocation,
            cameraPositionState.position.zoom
        )
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        isMapTouched(cameraPositionState.isMoving)
    }

    val parent = LocalView.current.parent

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        NaverMap(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter { event ->
                    when (event.actionMasked) {

                        MotionEvent.ACTION_DOWN -> {
//                        isMapTouched(true)
                            parent?.requestDisallowInterceptTouchEvent(true)
                        }

                        MotionEvent.ACTION_UP,
                        MotionEvent.ACTION_CANCEL -> {
//                        isMapTouched(false)
                            parent?.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                    false
                },
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isBuildingLayerGroupEnabled = true,
                isTransitLayerGroupEnabled = false,
                locationTrackingMode = LocationTrackingMode.NoFollow
            ),
            uiSettings = MapUiSettings(
                isZoomControlEnabled = true,
                isLocationButtonEnabled = false,
                isCompassEnabled = true,
                isScaleBarEnabled = true
            )
        ) {

            DrawSearchRadiusCircle(
                center = currentLocation,
                radiusInKm = searchRadius
            )

            DrawMarkers(
                userLocation = currentLocation,
                userLocationText = "내 위치",
                martEntities = state.nearbyMartEntities,
                martsWithValidCommissions = state.martsWithValidCommissions,
                onMartClick = onMartClick
            )
        }

        if (state.currentLocation != null) {
            RadiusButton(
                radius = searchRadius,
                onClick = onShowRadiusDialog,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }
    }
}


// 위치 마커 표시
@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun DrawMarkers(
    userLocation: LatLng? = null,
    userLocationText: String = "내 위치",
    martEntities: List<MartEntity> = emptyList(),
    martsWithValidCommissions: Set<String> = emptySet(),
    onMartClick: (MartEntity) -> Unit
) {
    // 사용자 위치 마커
    userLocation?.let { loc ->
        Marker(
            state = MarkerState(position = loc),
            captionText = userLocationText,
            icon = MarkerIcons.BLACK,
            iconTintColor = MapConstants.USER_LOCATION_MARKER_COLOR
        )
    }

    // 마트 마커들
    martEntities.forEach { mart ->
        val pos = LatLng(mart.latitude, mart.longitude)
        val hasCommission = martsWithValidCommissions.contains(mart.martName)

        if (hasCommission) {
            // 활성화 마커 (기본 아이콘 사용)
            Marker(
                state = MarkerState(position = pos),
                captionText = mart.martName,
                zIndex = MapConstants.MARKER_ZINDEX,
                onClick = {
                    onMartClick(mart)
                    true
                }
            )
        } else {
            // 비활성화 마커 (검은색 아이콘 + 회색 틴트)
            Marker(
                state = MarkerState(position = pos),
                captionText = mart.martName,
                zIndex = MapConstants.MARKER_ZINDEX,
                icon = MarkerIcons.BLACK,
                iconTintColor = MapConstants.MART_WITHOUT_COMMISSION_COLOR,
                onClick = {
                    onMartClick(mart)
                    true
                }
            )
        }
    }
}

// 검색 반경 원 표시
@Composable
private fun DrawSearchRadiusCircle(center: LatLng, radiusInKm: Float) {
    CircleOverlay(
        center = center,
        radius = radiusInKm * MapConstants.METERS_PER_KM, // km -> m
        color = MapConstants.CIRCLE_FILL_COLOR,
        outlineColor = MapConstants.CIRCLE_OUTLINE_COLOR,
        outlineWidth = MapConstants.CIRCLE_OUTLINE_WIDTH,
        zIndex = MapConstants.CIRCLE_ZINDEX
    )
}

// 위치 오류 메시지 표시
@Composable
private fun ShowLocationErrorMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentWidth(),
            color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.9f),
            shape = RoundedCornerShape(Dimens.DpSmall)
        ) {
            Text(
                text = "위치 정보를 가져올 수 없습니다.",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RadiusButton(
    radius: Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(Dimens.RoundCommon))
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "위치 검색 반경",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Text(
                text = if (radius >= 1f) "${radius.toInt()}km" else "${(radius * 1000).toInt()}m",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadiusSettingDialog(
    currentRadius: Float,
    onRadiusChange: (Float) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 헤더
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "위치 검색 반경 설정",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "닫기"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 현재 반경 값 표시
                Text(
                    text = "${(currentRadius * 1000).toInt()}m",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 슬라이더
                Slider(
                    value = currentRadius,
                    onValueChange = onRadiusChange,
                    valueRange = 0.1f..0.5f,
                    steps = 3, // 100, 200, 300, 400, 500m
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )

                // 눈금 텍스트
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("100m", style = MaterialTheme.typography.bodySmall)
                    Text("200m", style = MaterialTheme.typography.bodySmall)
                    Text("300m", style = MaterialTheme.typography.bodySmall)
                    Text("400m", style = MaterialTheme.typography.bodySmall)
                    Text("500m", style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 버튼 영역
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("취소")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("적용")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun RadiusButtonPreview() {
    RadiusButton(
        radius = 2.5f,
        onClick = {}
    )
}

@Composable
@Preview
fun RadiusSettingDialogPreview() {
    RadiusSettingDialog(
        currentRadius = 0.3f,
        onRadiusChange = {},
        onDismiss = {},
        onConfirm = {}
    )
}

@Composable
@Preview
fun MapSectionPreview() {
    val sampleMarts = listOf(
        MartEntity(
            martId = 0,
            martName = "example",
            latitude = 37.5665,
            longitude = 126.9780,
            sido = "sido",
            sigungu = "sigungu",
            dong = "dong",
            existCommission = 3
        ),
        MartEntity(
            martId = 0,
            martName = "example",
            latitude = 37.5665,
            longitude = 126.9780,
            sido = "sido",
            sigungu = "sigungu",
            dong = "dong",
            existCommission = 0
        ),
    )
    val sampleState = HomeUiState(
        currentLocation = MapConstants.DEFAULT_LOCATION,
        nearbyMartEntities = sampleMarts,
        martsWithValidCommissions = setOf("Mart A", "Mart C")
    )

    MapSection(
        state = sampleState,
        onMartClick = {},
        searchRadius = 0.3f
    )
}