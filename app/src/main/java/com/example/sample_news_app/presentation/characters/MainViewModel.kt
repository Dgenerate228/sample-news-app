package com.example.sample_news_app.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.domain.CharactersInteract
import com.example.sample_news_app.domain.model.NewCharacter
import com.example.sample_news_app.presentation.characters.model.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interact: CharactersInteract
) : ViewModel() {
    private val _screenState = MutableStateFlow<MainState>(MainState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        interact.fetchCharacters()
        _screenState.emit(
            when (val result = interact.characters.replayCache.firstOrNull()) {
                emptyList<NewCharacter>() -> MainState.Error
                null -> MainState.Error
                else -> MainState.Normal(characters = result)
            }
        )
    }
}
