package project.priceit.core.data.mapper

import project.priceit.core.model.MartEntity
import project.priceit.core.network.model.response.MartResponse

fun MartResponse.toEntity(): MartEntity =
    MartEntity(
        martId = id ?: -1,
        martName = martName ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        sido = sido,
        sigungu = sigungu,
        dong = dong,
        existCommission = existCommission ?:0
    )
