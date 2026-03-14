package dev.apollointhehouse.uoatech.data.api.scrapers

import dev.apollointhehouse.uoatech.data.state.Event
import kotlinx.coroutines.flow.Flow

interface EventScraper {
    suspend fun getEvents(): Flow<Event>
}