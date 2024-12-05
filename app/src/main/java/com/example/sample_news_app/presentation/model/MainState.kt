package com.example.sample_news_app.presentation.model

sealed interface MainState {
    data class Normal(val characters: List<NewCharacter>) : MainState
    data object Loading : MainState
    data object Error : MainState
}