package project.priceit.core.network.api

import project.priceit.core.network.model.response.MartResponse
import retrofit2.Response

interface MartApi {
    suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): Response<List<MartResponse>>
}