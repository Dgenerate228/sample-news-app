package com.example.sample_news_app.presentation.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.domain.CharactersInteract
import com.example.sample_news_app.navigation.CHARACTER_DETAIL_ID_KEY
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val interact: CharactersInteract,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id: String? = savedStateHandle[CHARACTER_DETAIL_ID_KEY]

    private val _screenState = MutableStateFlow<CharacterDetailsState>(CharacterDetailsState.Empty)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        _screenState.emit(
            when (val result = interact.getNewById(id)) {
                null -> CharacterDetailsState.Error
                else -> CharacterDetailsState.Normal(character = result)
            }
        )
    }
        }
