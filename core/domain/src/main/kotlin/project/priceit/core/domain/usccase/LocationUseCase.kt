package project.priceit.core.domain.usccase

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import project.priceit.core.domain.repo.LocationRepository
import project.priceit.core.domain.repo.MartRepository
import project.priceit.core.model.Location
import project.priceit.core.model.MartEntity

class LocationUseCase @Inject constructor(
    private val martRepository: MartRepository,
    private val locationRepository: LocationRepository
) {
    suspend fun getNearMartForLocation(
        latitude: Double,
        longitude: Double,
        radius: Float
    ): Result<List<MartEntity>> = martRepository.getNearMartLocation(
        latitude = latitude,
        longitude = longitude,
        radius = radius
    )

    @SuppressLint("MissingPermission")
    fun getLocationFlow(): Flow<Location> = callbackFlow {
        val fusedLocationClient = locationRepository.getFusedLocationClient()

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            3000L
        ).setMinUpdateIntervalMillis(2000)
            .build()

        val callback = object : LocationCallback() {

            override fun onLocationResult(result: LocationResult) {


                result.lastLocation?.let {
                    Log.d("LocationUseCase", "emit location")
                    trySend(Location(it.latitude, it.longitude))
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            callback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationClient.removeLocationUpdates(callback)
        }
    }
}
