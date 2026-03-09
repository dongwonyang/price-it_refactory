package project.priceit.core.data.repo

import com.google.android.gms.location.FusedLocationProviderClient
import jakarta.inject.Inject
import project.priceit.core.data.datasource.loaction.LocationDataSource
import project.priceit.core.domain.repo.LocationRepository

class LocationRepositoryImpl @Inject constructor(
    private val locationDatasource: LocationDataSource
): LocationRepository {
    override fun getFusedLocationClient(): FusedLocationProviderClient = locationDatasource.getFusedLocationClient()
}