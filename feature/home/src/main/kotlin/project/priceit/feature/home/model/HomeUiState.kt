package project.priceit.feature.home.model

import com.naver.maps.geometry.LatLng
import project.priceit.core.model.Location
import project.priceit.core.model.MartEntity
import project.priceit.core.model.RequestEntity

data class HomeUiState(
    val currentLocation: Location? = null,
    val nearbyMartEntities: List<MartEntity> = emptyList(),
    val martsWithValidCommissions: Set<String> = emptySet(), // workDate가 남아있는 의뢰가 있는 마트들의 이름
    val isRadiusDialogVisible: Boolean = false,
    val currentRequestList: List<RequestEntity> = emptyList(),
    val recommentRequestList: List<RequestEntity> = emptyList()
)


fun test(): HomeUiState = HomeUiState(
    nearbyMartEntities = listOf(
        MartEntity(
            martId = 1,
            martName = "Test Mart 1",
            latitude = 37.5600,
            longitude = 126.9280,
            sido = "Seoul",
            sigungu = "Gangnam-gu",
            dong = "Yeoksam-dong",
            existCommission = 1
        ),
        MartEntity(
            martId = 2,
            martName = "Test Mart 2",
            latitude = 37.5610,
            longitude = 126.9290,
            sido = "Seoul",
            sigungu = "Gangnam-gu",
            dong = "Yeoksam-dong",
            existCommission = 0
        )
    ),
    martsWithValidCommissions = setOf("Test Mart 1"),
    isRadiusDialogVisible = false,
)




fun Location.toLatLng() = LatLng(
    this.latitude,
    this.longitude
)