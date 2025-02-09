package com.example.pizzapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzapp.model.Caddy

@Composable
fun CaddyScreen(
    caddyViewModel: Caddy,
    onBack: () -> Unit, // Remplace NavController pour la navigation
    onPay: () -> Unit   // Callback pour le paiement
) {
    val pizzaOrders by caddyViewModel.pizzaOrders.collectAsState()
    val total = caddyViewModel.getTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Votre Commande",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            pizzaOrders.groupBy { it.name }.forEach { (pizzaName, orders) ->
                item {
                    PizzaTypeItem(pizzaName, orders)
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total à payer :",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${"%.2f".format(total)} €",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onPay, // Appel du callback de paiement
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Payer maintenant")
        }

        Button(
            onClick = onBack, // Retour au menu
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Retour au menu")
        }
    }
}

@Composable
fun PizzaTypeItem(pizzaName: String, orders: List<PizzaOrder>) {
    var expanded by remember { mutableStateOf(false) }
    val totalForType = orders.sumOf { it.price }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${orders.size}x $pizzaName",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${"%.2f".format(totalForType)} €",
                    style = MaterialTheme.typography.bodyLarge
                )

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Réduire" else "Afficher les détails",
                    modifier = Modifier.size(24.dp)
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Détail de la commande :",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                orders.forEachIndexed { index, order ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "1x $pizzaName",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "${"%.2f".format(order.price)} €",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "    Supplément fromage : ${order.extraCheese}g",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

// Modèle de données de commande
data class PizzaOrder(
    val name: String,
    val extraCheese: Int,
    val price: Double,
    val quantity: Int,
)
