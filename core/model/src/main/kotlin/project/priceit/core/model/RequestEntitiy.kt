package project.priceit.core.model

data class RequestEntity(
    val id: Int,
    val title: String,
    val location: String,
    val reward: Int,
    val category: String
)
