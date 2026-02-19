package project.priceit.util

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun LocalDateTime.toRelativeText(): String {
    val now = LocalDateTime.now()

    val minutes = ChronoUnit.MINUTES.between(this, now)
    val hours = ChronoUnit.HOURS.between(this, now)
    val days = ChronoUnit.DAYS.between(this, now)
    val weeks = days / 7
    val months = ChronoUnit.MONTHS.between(this, now)
    val years = ChronoUnit.YEARS.between(this, now)

    return when {
        minutes < 1 -> "방금 전"
        minutes < 60 -> "${minutes}분 전"
        hours < 24 -> "${hours}시간 전"
        days < 7 -> "${days}일 전"
        weeks < 5 -> "${weeks}주 전"
        months < 12 -> "${months}달 전"
        else -> "${years}년 전"
    }
}
