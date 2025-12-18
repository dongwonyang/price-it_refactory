package project.priceit.model

data class MartEntity(
    val martId: Int,
    val martName: String,
    val latitude: Double,
    val longitude: Double,
    val sido: String?,
    val sigungu: String?,
    val dong: String?,
    val existCommission: Int
)