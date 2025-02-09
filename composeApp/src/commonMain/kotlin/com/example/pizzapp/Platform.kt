package com.example.pizzapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform