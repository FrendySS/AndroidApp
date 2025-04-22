package com.example.animeviewer.presentation.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animeviewer.R
import com.example.animeviewer.presentation.ui.theme.AnimeViewerTheme
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(navController: NavController, animeId: String?) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimeViewerTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Аниме Детали") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = rememberAsyncImagePainter(R.drawable.ic_back),
                                contentDescription = "Назад"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(),
                exit = fadeOut(animationSpec = tween(300)) + slideOutVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Основное изображение аниме
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.anime_placeholder),
                        contentDescription = "Обложка аниме",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Название аниме (ID: $animeId)",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Описание аниме. Здесь будет длинный текст про аниме...",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.logo), // Используем Coil
                        contentDescription = "Логотип аниме",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    }
}