package com.example.pizzapp

import androidx.compose.runtime.*
import com.example.pizzapp.Screen.*
import com.example.pizzapp.model.Caddy
import com.example.pizzapp.model.PizzaViewModel
import com.example.pizzapp.screen.PizzaMenu
import com.example.pizzapp.screens.*


@Composable
fun App() {
    val pizzaViewModel = PizzaViewModel()
    val caddyViewModel = Caddy() // Gère le panier

    // Utilisation de `mutableStateOf` pour gérer la navigation
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Welcome) }

    // Affichage de l'écran en fonction de l'état actuel
    when (currentScreen) {
        is Screen.Welcome -> WelcomeScreen(
            onMenuClick = { currentScreen = Screen.Menu },
            onCaddyClick = { currentScreen = Screen.Caddy },
            onHistoryClick = { currentScreen = Screen.History }
        )

        is Screen.Menu -> PizzaMenu(
            pizzaViewModel = pizzaViewModel,
            onPizzaSelected = { pizzaId ->
                currentScreen = PizzaDetails(pizzaId) // Passer à l'écran de détails de la pizza
            },
            onBack = { currentScreen = Screen.Welcome } // Gérer le retour vers l'écran d'accueil

        )

        is Screen.PizzaDetails -> DetailPizza(
            pizzaId = (currentScreen as Screen.PizzaDetails).pizzaId,
            pizzaViewModel = pizzaViewModel,
            caddyViewModel = caddyViewModel,
            onBack = { currentScreen = Screen.Menu } // Revenir au menu
        )

        is Screen.Caddy -> CaddyScreen(
            caddyViewModel = caddyViewModel,
            onBack = { currentScreen = Screen.Menu }, // Retour au menu
            onPay = { currentScreen = Screen.History } // Aller à l'historique après paiement
        )


                /*
                is Screen.PizzaDetails -> DetailPizza(
                    pizzaId = (currentScreen as Screen.PizzaDetails).pizzaId,
                    pizzaViewModel = pizzaViewModel,
                    caddyViewModel = caddyViewModel,
                    onBack = { currentScreen = Screen.Menu } // Revenir au menu
                )

                is Screen.History -> HistoryScreen(
                    onBack = { currentScreen = Screen.Welcome },
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )*/
        Screen.Caddy -> TODO()
        Screen.History -> TODO()
        is Screen.PizzaDetails -> TODO()
    }
}


// Définition des différents écrans disponibles
sealed class Screen {
    object Welcome : Screen()
    object Menu : Screen()
    data class PizzaDetails(val pizzaId: Int) : Screen() // Écran de détails pour une pizza
    object Caddy : Screen()
    object History : Screen()
}
