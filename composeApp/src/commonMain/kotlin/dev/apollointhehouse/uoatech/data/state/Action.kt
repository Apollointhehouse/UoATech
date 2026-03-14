package dev.apollointhehouse.uoatech.data.state

sealed interface Action {
    object Loading : Action
    object None : Action
}
