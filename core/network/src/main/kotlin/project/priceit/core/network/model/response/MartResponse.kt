package project.priceit.core.network.model.response

data class MartResponse(
    val id: Int?,
    val martName: String?,
    val latitude: Double?,
    val longitude: Double?,
    val sido: String?,
    val sigungu: String?,
    val dong: String?,
    val existCommission: Int?
)