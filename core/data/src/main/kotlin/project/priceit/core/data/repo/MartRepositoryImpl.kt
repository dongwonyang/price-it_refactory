package project.priceit.core.data.repo

import jakarta.inject.Inject
import project.priceit.core.data.datasource.mart.MartDataSource
import project.priceit.core.data.di.FakeDataSource
import project.priceit.core.data.mapper.toEntity
import project.priceit.core.data.mapper.toResult
import project.priceit.core.domain.repo.MartRepository
import project.priceit.core.model.MartEntity

class MartRepositoryImpl @Inject constructor(
    @param:FakeDataSource private val martDataSource: MartDataSource
) : MartRepository {
    override suspend fun getNearMartLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): Result<List<MartEntity>> {
        return martDataSource.getNearMartLocation(
            latitude = latitude,
            longitude = longitude,
            radius = radius
        ).toResult(
            transform = {
                it.map { it.toEntity() }
            }
        )
    }
}