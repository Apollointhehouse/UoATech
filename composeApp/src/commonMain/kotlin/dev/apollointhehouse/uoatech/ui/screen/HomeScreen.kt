package dev.apollointhehouse.uoatech.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.apollointhehouse.uoatech.data.state.Action
import dev.apollointhehouse.uoatech.data.state.Event
import dev.apollointhehouse.uoatech.data.view.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val events = state.events

    SideEffect {
        viewModel.refreshEvents()
    }

    Column(
        modifier =
            Modifier.background(MaterialTheme.colorScheme.surface)
                .safeContentPadding()
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { viewModel.refreshEvents() }) {
            Text("Refresh")
        }

        Text(
            text = "Tech Events",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
        )
        when (state.action) {
            is Action.Loading -> Text("Loading...")
            is Action.None -> {
                Text("Events: ${state.events.size}")
                EventsList(events)
            }
        }
    }
}

@Composable
private fun EventsList(events: List<Event>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(events) { event -> EventItem(event) }
    }
}

@Composable
private fun EventItem(event: Event) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = event.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = event.club,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "${event.date} | ${event.startTime} - ${event.endTime} | ${event.location}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
