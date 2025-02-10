package com.example.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzapp.loadImage
import com.example.pizzapp.model.Pizza
import com.example.pizzapp.model.PizzaViewModel
import com.example.pizzapp.model.Caddy

@Composable
fun DetailPizza(
    pizza: Pizza,
    pizzaViewModel: PizzaViewModel,
    caddyViewModel: Caddy,
    onBack: () -> Unit
) {
    // Utilisez directement `pizza` dans le corps de votre composable
    val extraCheese = remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    caddyViewModel.addToCart(
                        PizzaOrder(
                            name = pizza.name,
                            extraCheese = extraCheese.value,
                            price = pizza.price + extraCheese.value * 0.5,
                            quantity = 1
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Ajouter au panier"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onBack) {
                Text("Retour")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = loadImage(pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier.size(300.dp).padding(16.dp)
            )

            Text(text = pizza.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Prix: ${pizza.price}€")
            Text(text = "Supplément Fromage: ${extraCheese.value}g")
            Slider(
                value = extraCheese.value.toFloat(),
                onValueChange = { newValue -> extraCheese.value = newValue.toInt() },
                valueRange = 0f..100f,
                steps = 4
            )
        }
    }
}
