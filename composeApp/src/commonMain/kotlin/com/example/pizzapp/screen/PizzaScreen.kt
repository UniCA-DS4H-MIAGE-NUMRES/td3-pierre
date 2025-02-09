package com.example.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzapp.loadImage
import com.example.pizzapp.model.PizzaViewModel
import java.awt.Image

@Composable
fun DetailPizza(
    pizzaId: Int,
    pizzaViewModel: PizzaViewModel,
    caddyViewModel: com.example.pizzapp.model.Caddy,
    onBack: () -> Unit
) {
    val pizzas = pizzaViewModel.pizzas.collectAsState(initial = emptyList()).value
    val pizza = pizzas.getOrNull(pizzaId) // Récupérer la pizza en fonction de son index
    val extraCheese = remember { mutableStateOf(0) }

    if (pizza != null) {

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        // Ajout au panier
                        /*
                        caddyViewModel.addToCart(
                            PizzaOrder(
                                name = pizza.name,
                                extraCheese = extraCheese,
                                price = pizza.price + extraCheese * 0.5,
                                quantity = 1
                            )
                        )
                        */
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
                // 🔹 Bouton retour
                Button(onClick = onBack) {
                    Text("Retour")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Chargement d'image multiplateforme
                Image(
                    painter = loadImage(pizza.image),
                    contentDescription = pizza.name,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Informations sur la pizza
                Text(text = pizza.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Prix: ${pizza.price}€")

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Slider pour le supplément fromage
                Text(text = "Supplément Fromage: ${extraCheese.value}g")
                Slider(
                    value = extraCheese.value.toFloat(),
                    onValueChange = { newValue ->
                        extraCheese.value = newValue.toInt()
                    },
                    valueRange = 0f..100f,
                    steps = 4
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    } else {
        Text("Pizza introuvable", modifier = Modifier.padding(16.dp))
    }
}