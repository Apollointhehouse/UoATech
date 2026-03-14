package dev.apollointhehouse.uoatech.data.api.scrapers

import dev.apollointhehouse.uoatech.data.api.scrapers.models.DevsEvent
import dev.apollointhehouse.uoatech.data.state.Event
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

private const val API = "https://cfsujuwbutitmycnwvdq.supabase.co/rest/v1/events"
private const val QUERY = "?select=id,title,description,image_url,tag,event_date,event_time,location,register_url&is_active=eq.true&order=event_date.asc"
private const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNmc3VqdXdidXRpdG15Y253dmRxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjYwMTczNjMsImV4cCI6MjA4MTU5MzM2M30.gZZl9h-4Dsj3loaUHptk9-m29d6VOJ_261FKJA0AXc4"

class DevsScraper(private val client: HttpClient) : EventScraper {
    override suspend fun getEvents(): Flow<Event> {
        val res: Array<DevsEvent> = client.get(urlString = API + QUERY) {
            headers {
                append("apikey", KEY)
            }

            userAgent("KTor Client: UoATech")
            bearerAuth(KEY)
        }.body()

        val result = flow {
            for (event in res) {
                val zone = TimeZone.of("Pacific/Auckland")
                val eventDate = LocalDate.parse(event.eventDate)
                val currentDate = Clock.System.todayIn(zone)
                if (currentDate > eventDate) continue

                val (startTime, endTime) = event.eventTime
                    .split("-")
                    .map { it.trim() }

                emit(Event(
                    name = event.title,
                    description = event.description,
                    date = eventDate,
                    startTime = startTime,
                    endTime = endTime,
                    club = "DEVS",
                    location = event.location
                ))
            }
        }

        return result
    }
}
