package com.example.sample_news_app.presentation.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.data.SouthParkAPI
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import com.example.sample_news_app.presentation.character_details.model.CharacterDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val UNKNOWN_MESSAGE = "Unknown"

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

    private suspend fun getSP(characterId: String): CharacterDetails? =
        withContext(Dispatchers.IO) {
            try {
                val result = spAPI.getCharacter(characterId)
                val body = result.body()
                if (result.isSuccessful && body != null) {
                    val character = body.data
                    CharacterDetails(
                        id = character.id.toString(),
                        name = character.name,
                        sex = character.sex,
                        hairColor = character.hairColor ?: UNKNOWN_MESSAGE,
                        occupation = character.occupation ?: UNKNOWN_MESSAGE,
                        religion = character.religion ?: UNKNOWN_MESSAGE
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
}