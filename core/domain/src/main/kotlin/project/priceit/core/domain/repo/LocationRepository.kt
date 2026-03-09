package project.priceit.core.domain.repo

import com.google.android.gms.location.FusedLocationProviderClient


interface LocationRepository {
    fun getFusedLocationClient(): FusedLocationProviderClient
}