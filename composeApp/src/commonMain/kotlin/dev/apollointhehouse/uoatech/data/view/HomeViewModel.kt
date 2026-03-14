package dev.apollointhehouse.uoatech.data.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.apollointhehouse.uoatech.data.api.API
import dev.apollointhehouse.uoatech.data.state.Action
import dev.apollointhehouse.uoatech.data.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state =
        MutableStateFlow(
            HomeScreenState(
                events = listOf()
            )
        )
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun refreshEvents() {
        viewModelScope.launch {
            _state.emit(state.value.copy(
                action = Action.Loading,
                events = listOf()
            ))

            _state.emit(state.value.copy(
                action = Action.None,
                events = API.getEvents()
            ))
        }
    }
}
