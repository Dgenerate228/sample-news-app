package com.example.sample_news_app.navigation

internal sealed class MainScreen(val route: String) {

    data object Characters : MainScreen("CHARACTERS")

    data object CharacterDetails : MainScreen("CHARACTER_DETAILS")
}