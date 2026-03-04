package project.priceit.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class HistoryType {
    WORK, REQUEST, POINT
}