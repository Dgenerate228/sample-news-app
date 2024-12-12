package com.example.sample_news_app.domain.mapper

import com.example.sample_news_app.presentation.characters.model.NewCharacter
import com.example.sample_news_app.presentation.character_details.model.CharacterDetails
import javax.inject.Inject

class CharacterDetailsMapper @Inject constructor() {

    fun map(character: NewCharacter?): CharacterDetails? = character?.let {
        CharacterDetails(
            id = character.id,
            name = character.name,
            sex = character.sex,
            religion = character.religion,
            hairColor = character.hairColor,
            occupation = character.occupation
        )
    }
}