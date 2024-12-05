package com.example.sample_news_app.navigation

internal sealed class SPScreen(val route: String) {

    data object Main: SPScreen("MAIN")

    data object CharacterDetails: SPScreen("CHARACTER_DETAILS")

}