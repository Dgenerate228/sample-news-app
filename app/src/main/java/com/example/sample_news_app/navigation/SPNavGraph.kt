package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_news_app.presentation.character_details.CharacterDetailsScreen
import com.example.sample_news_app.presentation.character_details.CharacterDetailsViewModel
import com.example.sample_news_app.presentation.characters.MainScreen


@Composable
fun SPNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "character_list") {
        composable("character_list") {
            MainScreen(
                viewModel = viewModel(),
                openCharacterDetails = { characterId ->
                    navController.navigate("character_details/$characterId")
                }
            )
        }
        composable("character_details/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")
            if (characterId != null) {
                val viewModel = viewModel<CharacterDetailsViewModel>()
                viewModel.loadData(characterId)
                CharacterDetailsScreen(
                    viewModel = viewModel,
                    navigationBack = { navController.popBackStack() })
            }
        }
    }
}