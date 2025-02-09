package com.example.pizzapp.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PizzaViewModel {

    // StateFlow pour observer les changements dans un environnement multiplateforme
    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas: StateFlow<List<Pizza>> = _pizzas

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        _pizzas.value = listOf(
            Pizza("Margherita", 8.0, "pizza1.png"),
            Pizza("Capricciosa", 10.0, "pizza2.png"),
            Pizza("Diavola", 9.0, "pizza3.png"),
            Pizza("Quattro Stagioni", 11.0, "pizza4.png"),
            Pizza("Quattro Formaggi", 12.0, "pizza5.png"),
            Pizza("Marinara", 7.0, "pizza6.png"),
            Pizza("Pepperoni", 9.0, "pizza7.png"),
            Pizza("Prosciutto", 10.0, "pizza8.png"),
            Pizza("Frutti di Mare", 13.0, "pizza9.png"),
        )
    }

    fun getPizzaById(id: Int): Pizza? {
        return _pizzas.value.getOrNull(id)
    }
}
