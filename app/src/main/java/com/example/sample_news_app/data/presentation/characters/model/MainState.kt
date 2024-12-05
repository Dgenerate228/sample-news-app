package com.example.sample_news_app.data.presentation.characters.model

import com.example.sample_news_app.presentation.model.NewCharacter

sealed interface MainState {
    data class Normal(val characters: List<NewCharacter>) : MainState
    data object Loading : MainState
    data object Error : MainState
}