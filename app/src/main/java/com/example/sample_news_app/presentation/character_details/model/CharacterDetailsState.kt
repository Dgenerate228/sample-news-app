package com.example.sample_news_app.presentation.character_details.model

sealed interface CharacterDetailsState {
        data class Normal(val new: DetailCharacterList) : CharacterDetailsState
        data object Empty : CharacterDetailsState
        data object Error : CharacterDetailsState
    }
