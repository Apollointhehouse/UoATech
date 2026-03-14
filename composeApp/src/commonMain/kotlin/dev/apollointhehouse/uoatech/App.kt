package dev.apollointhehouse.uoatech

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.apollointhehouse.uoatech.ui.screen.HomeScreen
import kotlinx.serialization.Serializable

@Serializable object Home

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Home) {
            composable<Home> { HomeScreen() }
        }
    }
}
