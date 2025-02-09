package com.example.pizzapp

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import com.example.pizzapp.screens.*

@Composable
fun App() {
    // Initialisation de la base de données Room pour Android
    /*
    val context = LocalContext.current
    val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "pizza_database"
    ).build()

    val caddyViewModel: Caddy = viewModel()
*/
    // Utilisation de `mutableStateOf` pour gérer la navigation
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Welcome) }


    //aller sur welcome screen
    WelcomeScreen(
        onMenuClick = { currentScreen = Screen.Menu },
        onCaddyClick = { currentScreen = Screen.Caddy },
        onHistoryClick = { currentScreen = Screen.History }
    )

    /*
    // Affichage de l'écran en fonction de l'état actuel
    when (currentScreen) {
        is Screen.Welcome -> WelcomeScreen(
            onMenuClick = { currentScreen = Screen.Menu },
            onCaddyClick = { currentScreen = Screen.Caddy },
            onHistoryClick = { currentScreen = Screen.History }
        )

        is Screen.Menu -> PizzaMenu(
            onBack = { currentScreen = Screen.Welcome },
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
        is Screen.Caddy -> CaddyScreen(
            onBack = { currentScreen = Screen.Welcome },
            caddyViewModel = caddyViewModel,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
        is Screen.History -> HistoryScreen(
            onBack = { currentScreen = Screen.Welcome },
            database = database,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
    }*/
}

// Définition des différents écrans disponibles
sealed class Screen {
    object Welcome : Screen()
    object Menu : Screen()
    object Caddy : Screen()
    object History : Screen()
}
