package dev.apollointhehouse.uoatech.data.state

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name: String,
    val description: String,
    val date: LocalDate,
    val time: String,
    val club: String,
    val location: String,
    val registerURL: String = "",
)
