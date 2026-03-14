@file:OptIn(ExperimentalCoroutinesApi::class)
package dev.apollointhehouse.uoatech.data.api

import dev.apollointhehouse.uoatech.data.api.scrapers.DevsScraper
import dev.apollointhehouse.uoatech.data.api.scrapers.EventScraper
import dev.apollointhehouse.uoatech.data.state.Event
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

object API {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    private val scrapers: Flow<EventScraper> = flowOf(
        DevsScraper(client)
    )

    suspend fun getEvents(): List<Event> {
        return scrapers
            .flatMapMerge { it.getEvents() }
            .toList()
            .sortedBy { it.date }
    }
}


