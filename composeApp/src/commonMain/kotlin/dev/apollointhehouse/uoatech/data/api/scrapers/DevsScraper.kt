package dev.apollointhehouse.uoatech.data.api.scrapers

import dev.apollointhehouse.uoatech.data.api.scrapers.models.DevsEvent
import dev.apollointhehouse.uoatech.data.state.Event
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

class DevsScraper : EventScraper {
    private val supabase = createSupabaseClient(
        supabaseUrl = "https://cfsujuwbutitmycnwvdq.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNmc3VqdXdidXRpdG15Y253dmRxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjYwMTczNjMsImV4cCI6MjA4MTU5MzM2M30.gZZl9h-4Dsj3loaUHptk9-m29d6VOJ_261FKJA0AXc4"
    ) {
        install(Postgrest)
    }

    override suspend fun getEvents(): Flow<Event> = flow {
        for (event in getDevsEvents()) {
            val zone = TimeZone.of("Pacific/Auckland")
            val eventDate = LocalDate.parse(event.eventDate)
            val currentDate = Clock.System.todayIn(zone)
            if (currentDate > eventDate) continue

            val registerUrl = event.registerUrl.ifEmpty { "https://www.devsuoa.com/events#${event.id}" }

            emit(Event(
                name = event.title,
                description = event.description,
                date = eventDate,
                time = event.eventTime,
                club = "DEVS",
                location = event.location,
                registerURL = registerUrl
            ))
        }
    }

    private suspend fun getDevsEvents(): List<DevsEvent> = supabase
        .from("events")
        .select(Columns.list("title", "description", "event_date", "event_time", "location", "register_url", "id"))
        .decodeList()
}
