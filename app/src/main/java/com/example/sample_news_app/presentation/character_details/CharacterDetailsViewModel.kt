package com.example.sample_news_app.presentation.character_details

import androidx.lifecycle.ViewModel
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import kotlinx.coroutines.flow.MutableStateFlow


class CharacterDetailsViewModel: ViewModel() {
    lateinit var id: String

    private val _screenState = MutableStateFlow<CharacterDetailsState>(CharacterDetailsState.Empty)
    val screenState : MutableStateFlow<CharacterDetailsState> get() = _screenState

//    init {
//        loadData()
//    }

    private fun loadData(loadData: CharacterDetailsState) {
        _screenState.value = loadData


    }
}