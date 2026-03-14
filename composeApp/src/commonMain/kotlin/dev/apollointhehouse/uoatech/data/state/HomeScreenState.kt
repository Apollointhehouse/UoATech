package dev.apollointhehouse.uoatech.data.state

data class HomeScreenState(
    val action: Action = Action.Loading,
    val events: List<Event> = listOf()
)
