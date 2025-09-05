package com.example.seekhotest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seekhotest.ui.home.AnimeDetailScreen
import com.example.seekhotest.ui.home.AnimeScreen
import com.example.seekhotest.ui.home.AnimeViewModel

@Composable
fun AnimeApp(viewModel: AnimeViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "anime_list"
    ) {
        composable("anime_list") {
            AnimeScreen(
                viewModel = viewModel,
                onAnimeClick = { animeId ->
                    navController.navigate("anime_detail/$animeId")
                },
                modifier
            )
        }
        composable("anime_detail/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
            AnimeDetailScreen(animeId = animeId, viewModel = viewModel, modifier = modifier)
        }
    }
}