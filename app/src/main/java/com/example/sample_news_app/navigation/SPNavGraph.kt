package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample_news_app.data.presentation.character_details.CharacterDetailsScreen
import com.example.sample_news_app.presentation.MainScreen


@Composable
internal fun SPNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPScreen.Main.route,
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(route = SPScreen.Main.route) { MainScreen(
        openCharacterDetails = {navController.navigate(SPScreen.CharacterDetails.route)}
    ) }
    composable(route = SPScreen.CharacterDetails.route) { CharacterDetailsScreen() }

}