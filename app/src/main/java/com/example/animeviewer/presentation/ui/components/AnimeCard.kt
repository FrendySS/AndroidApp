package com.example.animeviewer.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animeviewer.domain.models.AnimeModel

@Composable
fun AnimeCard(anime: AnimeModel, navController: NavController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("animeDetail/${anime.id}")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = anime.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = anime.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
