package com.example.sample_news_app.domain.mapper
import com.example.sample_news_app.domain.model.NewCharacter as CharacterDomain
import com.example.sample_news_app.presentation.characters.model.NewCharacter as CharacterPresentation

import javax.inject.Inject

class CharactersMapper @Inject constructor() {

    fun mapCharacters(characters: List<CharacterDomain>): List<CharacterPresentation> = characters.map(::mapCharacter)

    private fun mapCharacter(character: CharacterDomain): CharacterPresentation = CharacterPresentation(
        id = character.id,
        sex = character.sex,
        name = character.name,
        hairColor = character.hairColor,
        occupation = character.occupation,
        religion = character.religion
    )
}