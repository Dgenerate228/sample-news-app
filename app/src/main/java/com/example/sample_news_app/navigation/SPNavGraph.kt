package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_news_app.presentation.character_details.CharacterDetailsScreen
import com.example.sample_news_app.presentation.character_details.CharacterDetailsViewModel
import com.example.sample_news_app.presentation.characters.MainScreen

private const val CHARACTER_LIST_ROUTE = "CHARACTER_LIST"
private const val CHARACTER_DETAILS_ROUTE = "CHARACTER_DETAILS"
private const val CHARACTER_DETAILS_ID = "CHARACTER_ID"

@Composable
fun SPNavGraph(navController: NavHostController = rememberNavController()) =
    NavHost(navController = navController, startDestination = CHARACTER_LIST_ROUTE) {
        composable(CHARACTER_LIST_ROUTE) {
            MainScreen(
                openCharacterDetails = { characterId ->
                    navController.navigate("$CHARACTER_DETAILS_ROUTE/$characterId")
                }
            )
        }
        composable("$CHARACTER_DETAILS_ROUTE/{$CHARACTER_DETAILS_ID}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString(CHARACTER_DETAILS_ID)
            if (characterId != null) {
                val viewModel = viewModel<CharacterDetailsViewModel>()
                viewModel.loadData(characterId)
                CharacterDetailsScreen(
                    viewModel = viewModel,
                    navigationBack = { navController.popBackStack() })
            }
        }
    }
