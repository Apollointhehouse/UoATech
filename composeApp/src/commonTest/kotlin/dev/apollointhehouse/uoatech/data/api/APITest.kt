package dev.apollointhehouse.uoatech.data.api

import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class APITest {
    @Test fun `test retrieval of website dom`() = runTest { API.getEvents() }
}
