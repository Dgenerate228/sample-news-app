package com.example.sample_news_app.domain

import com.example.sample_news_app.domain.model.NewCharacter

interface ICharactersRepository {

    suspend fun getCharacters(): List<NewCharacter>
}