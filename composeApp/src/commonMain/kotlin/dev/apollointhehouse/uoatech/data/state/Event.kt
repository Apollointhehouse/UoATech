package dev.apollointhehouse.uoatech.data.state

import kotlinx.datetime.LocalDate

data class Event(
    val name: String,
    val description: String,
    val date: LocalDate,
    val startTime: String,
    val endTime: String,
    val club: String,
    val location: String,
)
