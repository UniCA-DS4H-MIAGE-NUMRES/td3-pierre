package com.example.pizzapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzapp.model.Pizza
import com.example.pizzapp.model.PizzaViewModel
import com.example.pizzapp.loadImage

@Composable
fun PizzaCard(pizza: Pizza, modifier: Modifier = Modifier, onClickPizza: () -> Unit) {

    Card(
        modifier = modifier,
        onClick = onClickPizza
    ) {
        Column(modifier = modifier) {
            Image(
                painter = loadImage(pizza.image),
                contentDescription = pizza.name,
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = pizza.name,
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )


            Text(
                text = "Prix = ${pizza.price}€",
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun PizzaMenu(
    pizzaViewModel: PizzaViewModel,
    modifier: Modifier = Modifier,
    onPizzaSelected: (Int) -> Unit,
    onBack: () -> Unit // Ajouter un callback pour gérer le retour
) {
    val pizzas by pizzaViewModel.pizzas.collectAsState()

    Column(modifier = modifier) {
        // Bouton de retour en haut
        androidx.compose.material3.Button(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start) // Alignement à gauche
        ) {
            Text("Retour")
        }

        // Liste des pizzas
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            itemsIndexed(pizzas) { index, pizza -> // Utilisation de `itemsIndexed` pour récupérer l'index
                PizzaCard(
                    pizza = pizza,
                    modifier = Modifier.padding(16.dp),
                    onClickPizza = {
                        onPizzaSelected(index) // Passer l'ID de la pizza cliquée
                    }
                )
            }
        }
    }
}