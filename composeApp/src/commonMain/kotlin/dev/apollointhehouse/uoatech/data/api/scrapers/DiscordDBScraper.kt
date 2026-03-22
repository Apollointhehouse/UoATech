package dev.apollointhehouse.uoatech.data.api.scrapers

import dev.apollointhehouse.uoatech.data.state.Event
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

class DiscordDBScraper : EventScraper {
    private val supabase = createSupabaseClient(
        supabaseUrl = "https://jaegtwcvfelfbtlmbpnj.supabase.co",
        supabaseKey = "sb_publishable_Y_d4fjTYAD-iRJjQIaVdew_F3znHhgj"
    ) {
        install(Postgrest)
    }

    override suspend fun getEvents(): Flow<Event> = flow {
        for (event in getDiscordEvents()) {
            val zone = TimeZone.of("Pacific/Auckland")
            val currentDate = Clock.System.todayIn(zone)
            if (currentDate > event.date) continue

            emit(event)
        }
    }

    private suspend fun getDiscordEvents(): List<Event> = supabase
        .from("events")
        .select(Columns.ALL)
        .decodeList()
}
