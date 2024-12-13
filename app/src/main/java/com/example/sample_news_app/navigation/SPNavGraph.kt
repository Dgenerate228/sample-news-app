package com.example.sample_news_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.sample_news_app.presentation.character_details.CharacterDetailsScreen
import com.example.sample_news_app.presentation.characters.MainScreen

const val CHARACTER_DETAIL_ID_KEY = "id"

@Composable
internal fun SPNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainScreen.Characters.route
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(route = MainScreen.Characters.route) {
        MainScreen(
            openCharacterDetails = { id -> navController.navigate("${MainScreen.CharacterDetails.route}/$id") }
        )
    }
    composable(
        route = "${MainScreen.CharacterDetails.route}/{$CHARACTER_DETAIL_ID_KEY}",
        arguments = listOf(
            navArgument(CHARACTER_DETAIL_ID_KEY) {
                type = NavType.StringType
            },
        ),
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString(CHARACTER_DETAIL_ID_KEY)
        id?.let {
            CharacterDetailsScreen(
                navigationBack = { navController.popBackStack() }
            )
        }
    }
}
