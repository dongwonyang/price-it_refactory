package project.priceit.core.data.datasource.mart

import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.MartResponse
import javax.inject.Inject

class FakeMartDataSource @Inject constructor(): MartDataSource {
    override suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): ApiResponse<List<MartResponse>> {
        return ApiResponse.Success(
            listOf(
                MartResponse(
                    id = 1,
                    martName = "Test Mart 1",
                    latitude = 37.5600,
                    longitude = 126.9280,
                    sido = "Seoul",
                    sigungu = "Gangnam-gu",
                    dong = "Yeoksam-dong",
                    existCommission = 1
                ),
                MartResponse(
                    id = 2,
                    martName = "Test Mart 2",
                    latitude = 37.5610,
                    longitude = 126.9290,
                    sido = "Seoul",
                    sigungu = "Gangnam-gu",
                    dong = "Yeoksam-dong",
                    existCommission = 0
                )
            )
        )
    }

}