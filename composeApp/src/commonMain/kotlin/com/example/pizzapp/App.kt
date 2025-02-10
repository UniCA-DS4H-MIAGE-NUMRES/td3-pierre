package com.example.pizzapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pizzapp.model.Caddy
import com.example.pizzapp.model.PizzaViewModel
import com.example.pizzapp.screen.PizzaMenu
import com.example.pizzapp.screens.*

@Composable
fun App() {
    MaterialTheme {
        // Initialisation des ViewModels
        val pizzaViewModel = remember { PizzaViewModel() }
        val caddyViewModel = remember { Caddy() }

        // Gestion de l'écran courant
        var currentScreen by remember { mutableStateOf("welcome") }

        when (val screen = currentScreen) {
            "welcome" -> WelcomeScreen(
                onMenuClick = { currentScreen = "menu" },
                onCaddyClick = { currentScreen = "cart" },
                onHistoryClick = { currentScreen = "orderHistory" }
            )
            "menu" -> PizzaMenu(
                pizzaViewModel = pizzaViewModel,
                onPizzaSelected = { pizzaId ->
                    currentScreen = "detail/$pizzaId"
                },
                onBack = { currentScreen = "welcome" }
            )
            "cart" -> CaddyScreen(
                caddyViewModel = caddyViewModel,
                onBack = { currentScreen = "menu" },
                onPay = { currentScreen = "orderHistory" }
            )
            "orderHistory" -> HistoryScreen(
                onBack = { currentScreen = "welcome" }
            )
            else -> {
                // Gérer les écrans de détails avec des paramètres
                if (screen.startsWith("detail/")) {
                    val pizzaId = screen.removePrefix("detail/").toIntOrNull()
                    val pizza = pizzaViewModel.getPizzaById(pizzaId ?: -1)
                    if (pizza != null) {
                        DetailPizza(
                            pizza = pizza,
                            pizzaViewModel = pizzaViewModel,
                            caddyViewModel = caddyViewModel,
                            onBack = { currentScreen = "menu" }
                        )

                    } else {
                        androidx.compose.material3.Text(
                            "Pizza introuvable",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
