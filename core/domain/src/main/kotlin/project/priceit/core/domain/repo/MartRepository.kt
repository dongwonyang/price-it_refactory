package project.priceit.core.domain.repo

import project.priceit.core.model.MartEntity

interface MartRepository {
    suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): Result<List<MartEntity>>
}