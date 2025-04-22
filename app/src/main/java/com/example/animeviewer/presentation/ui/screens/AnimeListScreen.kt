package com.example.animeviewer.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.animeviewer.presentation.ui.components.AnimeCard
import com.example.animeviewer.presentation.ui.components.SearchBar
import com.example.animeviewer.data.AnimeRepository
import com.example.animeviewer.R

@Composable
fun AnimeListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    val animeList = remember { AnimeRepository.getAnimeList() }

    // Имитация загрузки данных
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(query = searchQuery, onQueryChange = { searchQuery = it })
        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            AnimeLoadingAnimation()
        } else {
            val filteredList = animeList.filter { it.title.contains(searchQuery, ignoreCase = true) }
            LazyColumn {
                items(filteredList) { anime ->
                    AnimeCard(anime, navController)
                }
            }
        }
    }
}

@Composable
fun AnimeLoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)
        )
    }
}
