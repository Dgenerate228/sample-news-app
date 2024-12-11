package com.example.sample_news_app.data.mapper

import com.example.sample_news_app.data.model.SouthPark
import com.example.sample_news_app.presentation.characters.model.NewCharacter

import javax.inject.Inject

class CharacterMapper @Inject constructor() {

    fun mapCharacters(characters: SouthPark): List<NewCharacter> =
        characters.dataList.mapIndexed { index, dataList ->
            mapCharacter(index.toString(), dataList)
        }

    //Узнать, нужен ли мне id. Если его убрать, возникает ошибка в функции выше
    private fun mapCharacter(id: String, character: SouthPark.DataList): NewCharacter =
        NewCharacter(
            id = id,
            sex = character.sex,
            name = character.name,
            religion = character.religion,
            hairColor = character.hairColor,
            occupation = character.occupation
        )
}