package com.example.sample_news_app.presentation.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.data.SouthParkAPI
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import com.example.sample_news_app.presentation.character_details.model.DetailCharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterDetailsViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<CharacterDetailsState>(CharacterDetailsState.Empty)
    val screenState: MutableStateFlow<CharacterDetailsState> get() = _screenState

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://spapi.dev/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val spAPI = retrofit.create(SouthParkAPI::class.java)

    fun loadData(characterId: String) = viewModelScope.launch {
        _screenState.emit(
            when (val result = getSP(characterId)) {
                null -> CharacterDetailsState.Error
                else -> CharacterDetailsState.Normal(character = result)
            }
        )
    }

    private suspend fun getSP(characterId: String): DetailCharacterList? =
        withContext(Dispatchers.IO) {
            try {
                val result = spAPI.getCharacter(characterId)
                val body = result.body()
                if (result.isSuccessful && body != null) {
                    val character = body.data
                    DetailCharacterList(
                        id = character.id.toString(),
                        name = character.name,
                        sex = character.sex,
                        hairColor = character.hairColor ?: "Unknown",
                        occupation = character.occupation ?: "Unknown",
                        religion = character.religion ?: "Unknown"
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
}