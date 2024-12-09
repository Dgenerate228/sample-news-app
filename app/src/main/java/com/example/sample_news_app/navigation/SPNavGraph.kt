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

    NavHost(navController = navController, startDestination = "CHARACTER_LIST") {
        composable("CHARACTER_LIST") {
            MainScreen(
                viewModel = viewModel(),
                openCharacterDetails = { characterId ->
                    navController.navigate("CHARACTER_DETAILS/$characterId")
                }
            )
        }
        composable("CHARACTER_DETAILS/{CHARACTER_ID}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("CHARACTER_ID")
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