package project.priceit.model

import kotlinx.serialization.Serializable

@Serializable
enum class HistoryType {
    WORK, REQUEST, POINT
}