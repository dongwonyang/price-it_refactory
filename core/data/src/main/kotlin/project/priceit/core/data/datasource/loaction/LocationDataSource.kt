package project.priceit.core.data.datasource.loaction

import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import project.priceit.core.model.Location

class LocationDataSource @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) {

    fun getFusedLocationClient() = fusedLocationClient
}