package dev.apollointhehouse.uoatech.data.api.scrapers.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevsEvent(
    val title: String,
    val description: String,
    @SerialName("event_date")
    val eventDate: String,
    @SerialName("event_time")
    val eventTime: String,
    val location: String,
    @SerialName("register_url")
    val registerUrl: String = "",
    val id: String
)