package com.example.sample_news_app.data.presentation.character_details.model

import com.example.sample_news_app.presentation.model.NewCharacter

    sealed interface CharacterDetailsState {
        data class Normal(val new: NewCharacter) : CharacterDetailsState
        data object Empty : CharacterDetailsState
        data object Error : CharacterDetailsState
    }
