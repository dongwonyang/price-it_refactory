package project.priceit.core.data.datasource.mart

import jakarta.inject.Inject
import project.priceit.core.network.api.MartApi
import project.priceit.core.network.model.ApiResponse
import project.priceit.core.network.model.response.MartResponse
import project.priceit.core.network.util.runRemote

class RetrofitMartDataSource @Inject constructor(
    private val martApi: MartApi
) : MartDataSource {
    override suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): ApiResponse<List<MartResponse>> =
        runRemote { martApi.getNearMartLocation(latitude, longitude, radius) }
}