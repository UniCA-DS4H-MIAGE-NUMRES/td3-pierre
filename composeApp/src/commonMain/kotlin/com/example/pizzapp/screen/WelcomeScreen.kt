package com.example.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzapp.loadImage

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    // Utilisation de mutableStateOf pour gérer la navigation entre les écrans
    var currentScreen by remember { mutableStateOf(Screen.Welcome) }

    when (currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            onMenuClick = { currentScreen = Screen.Menu },
            onCaddyClick = { currentScreen = Screen.Caddy },
            onHistoryClick = { currentScreen = Screen.History }
        )
        Screen.Menu -> MenuScreen(onBack = { currentScreen = Screen.Welcome })
        Screen.Caddy -> CaddyScreen(onBack = { currentScreen = Screen.Welcome })
        Screen.History -> HistoryScreen(onBack = { currentScreen = Screen.Welcome })
    }
}

@Composable
fun WelcomeScreen(
    onMenuClick: () -> Unit,
    onCaddyClick: () -> Unit,
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Chargement de l'image via loadImage
        Image(
            painter = loadImage("logo.jpg"),
            contentDescription = "Logo de PizzApp",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "Bienvenue sur PizzApp !",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Boutons pour naviguer
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onMenuClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Voir le menu")
            }
            Button(
                onClick = onCaddyClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Voir la commande")
            }
            Button(
                onClick = onHistoryClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Historique de vos commandes")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun MenuScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Menu Screen")
        Button(onClick = onBack) {
            Text(text = "Retour")
        }
    }
}

@Composable
fun CaddyScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Caddy Screen")
        Button(onClick = onBack) {
            Text(text = "Retour")
        }
    }
}

@Composable
fun HistoryScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Historique des commandes")
        Button(onClick = onBack) {
            Text(text = "Retour")
        }
    }
}

enum class Screen {
    Welcome, Menu, Caddy, History
}
