package project.priceit.feature.home

import com.naver.maps.geometry.LatLng
import project.priceit.model.Location
import project.priceit.model.MartEntity

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(
        val currentLocation: Location? = null,
        val searchRadius: Float = 0.5f,
        val nearbyMartEntities: List<MartEntity> = emptyList(),
        val martsWithValidCommissions: Set<String> = emptySet(), // workDate가 남아있는 의뢰가 있는 마트들의 이름
        val isRadiusDialogVisible: Boolean = false,
        val currentRequestList: List<RequestItem> = emptyList(),
        val recommentRequestList:List<RequestItem> = emptyList()
        ) : HomeUiState()

    data class Error(val errorMessage: String) : HomeUiState()

    companion object {
        fun test() = Success(
            currentLocation = Location(37.5596, 126.9277),
            searchRadius = 0.5f,
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
            currentRequestList = sampleRequests,
            recommentRequestList = sampleRequests
        )
    }
}

data class RequestItem(val title: String, val location: String, val reward: String, val id: Int = 0)

val sampleRequests = listOf(
    RequestItem("딸기 한 팩 가격", "수석 마트", "리워드 : 15p"),
    RequestItem("진라면 한 묶음 가격", "상암 홈플러스", "리워드 : 10p")
)


fun Location.toLatLng() = LatLng(
    this.latitude,
    this.longitude
)