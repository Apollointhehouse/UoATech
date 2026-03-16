package dev.apollointhehouse.uoatech

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@ExperimentalWasmJsInterop
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

@OptIn(ExperimentalWasmJsInterop::class)
@Suppress("Unused")
private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport { App() }
}
