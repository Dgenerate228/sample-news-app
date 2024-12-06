package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_news_app.presentation.character_details.CharacterDetailsScreen
import com.example.sample_news_app.presentation.character_details.CharacterDetailsViewModel
import com.example.sample_news_app.presentation.characters.MainScreen


@Composable
internal fun SPNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPScreen.Main.route,
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(route = SPScreen.Main.route) {
        MainScreen(
            openCharacterDetails = { navController.navigate(SPScreen.CharacterDetails.route) }
        )
    }
    composable(route = SPScreen.CharacterDetails.route) {
        CharacterDetailsScreen(
            navigationBack = { navController.popBackStack() },
            viewModel = CharacterDetailsViewModel()
        )
    }

}