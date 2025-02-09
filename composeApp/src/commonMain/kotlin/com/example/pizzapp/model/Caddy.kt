package com.example.pizzapp.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.pizzapp.screens.PizzaOrder

class Caddy {

    private val _pizzaOrders = MutableStateFlow<List<PizzaOrder>>(emptyList())
    val pizzaOrders: StateFlow<List<PizzaOrder>> = _pizzaOrders

    fun addToCart(order: PizzaOrder) {
        _pizzaOrders.update { currentOrders -> currentOrders + order }
    }

    fun removeFromCart(order: PizzaOrder) {
        _pizzaOrders.update { currentOrders -> currentOrders - order }
    }

    fun getTotalPrice(): Double {
        return _pizzaOrders.value.sumOf { it.price }
    }
}