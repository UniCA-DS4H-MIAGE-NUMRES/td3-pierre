package com.example.pizzapp.model

class Pizza (val name: String, val price: Double, val image: String) {
    override fun toString(): String {
        return "Pizza(name='$name', price=$price)"
    }
}