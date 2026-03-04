package project.priceit.core.data.datasource.mart

import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.MartResponse
import retrofit2.Response

interface MartDataSource {
    suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): ApiResponse<List<MartResponse>>
}