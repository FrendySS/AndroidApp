package com.example.animeviewer.presentation

import AnimatedAppIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animeviewer.presentation.ui.screens.AnimeDetailScreen
import com.example.animeviewer.presentation.ui.theme.AnimeViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                AnimatedAppIcon { showSplash = false }
            } else {
                AnimeViewerTheme {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "animeList") {
        composable("animeList") { AnimeListScreen(navController) }
        composable("animeDetail/{animeId}") { backStackEntry ->
            AnimeDetailScreen(navController, backStackEntry.arguments?.getString("animeId"))
        }
    }
}

@Composable
fun AnimeListScreen(navController: androidx.navigation.NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val animeList = listOf(
        "Naruto", "Attack on Titan", "One Piece", "Demon Slayer", "Death Note"
    )
    val filteredList = animeList.filter { it.contains(searchText.text, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Поиск аниме") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(filteredList) { anime ->
                AnimeCard(anime, onClick = { navController.navigate("animeDetail/$anime") })
            }
        }
    }
}

@Composable
fun AnimeCard(title: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
