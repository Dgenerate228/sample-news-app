package com.example.sample_news_app.domain

import com.example.sample_news_app.domain.mapper.CharacterDetailsMapper
import com.example.sample_news_app.domain.mapper.CharactersMapper
import com.example.sample_news_app.presentation.character_details.model.CharacterDetails
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.sample_news_app.presentation.characters.model.NewCharacter as PresentationCharacter


@Singleton
class CharactersInteract @Inject  constructor(
    private val charactersRepository: ICharactersRepository,
    private val charactersMapper: CharactersMapper,
    private val characterDetailsMapper: CharacterDetailsMapper,
) {

    private val _characters = MutableSharedFlow<List<PresentationCharacter>>(replay = 1)
    val characters = _characters.asSharedFlow()

    suspend fun fetchCharacters() = _characters.emit(
        charactersMapper.mapCharacters(
            charactersRepository.getCharacters()
        )
    )

    fun getNewById(id: String?): CharacterDetails? =
        characterDetailsMapper.map(
            _characters.replayCache
                .firstOrNull()
                ?.firstOrNull { it.id == id }
        )
}