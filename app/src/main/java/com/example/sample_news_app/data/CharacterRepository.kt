package com.example.sample_news_app.data

import com.example.sample_news_app.data.mapper.CharacterMapper
import com.example.sample_news_app.domain.model.NewCharacter
import com.example.sample_news_app.domain.ICharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val spAPI: SouthParkAPI,
    private val southParkMapper: CharacterMapper,
) : ICharactersRepository {

    override suspend fun getCharacters(): List<NewCharacter> = withContext(Dispatchers.IO) {
        try {
            val result = spAPI.getSP()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return@withContext southParkMapper.mapCharacters(body)
            } else {
                return@withContext emptyList()
            }
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }
}
