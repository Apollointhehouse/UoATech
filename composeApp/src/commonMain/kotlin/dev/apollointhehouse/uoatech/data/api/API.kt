@file:OptIn(ExperimentalCoroutinesApi::class)
package dev.apollointhehouse.uoatech.data.api

import dev.apollointhehouse.uoatech.data.api.scrapers.DevsScraper
import dev.apollointhehouse.uoatech.data.api.scrapers.DiscordDBScraper
import dev.apollointhehouse.uoatech.data.api.scrapers.EventScraper
import dev.apollointhehouse.uoatech.data.state.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

object API {
    private val scrapers: Flow<EventScraper> = flowOf(
        DevsScraper(), DiscordDBScraper()
    )

    suspend fun getEvents(): List<Event> {
        return scrapers
            .flatMapMerge { it.getEvents() }
            .toList()
            .sortedBy { it.date }
    }
}


