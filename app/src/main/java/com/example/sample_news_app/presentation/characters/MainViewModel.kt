package com.example.sample_news_app.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_news_app.data.SouthParkAPI
import com.example.sample_news_app.presentation.characters.model.NewCharacter
import com.example.sample_news_app.presentation.characters.model.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _screenState = MutableStateFlow<MainState>(MainState.Loading)
    val screenState = _screenState.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://spapi.dev/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val spAPI = retrofit.create(SouthParkAPI::class.java)

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        _screenState.emit(
            when (val result = getSP()) {
                emptyList<NewCharacter>() -> MainState.Error
                else -> MainState.Normal(characters = result)
            }
        )
    }

    private suspend fun getSP(): List<NewCharacter> = withContext(Dispatchers.IO) {
        try {
            val result = spAPI.getSP()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                body.dataList.map { new ->
                    NewCharacter(
                        id = new.id,
                        name = new.name,
                        sex = new.sex,
                        hairColor = new.hairColor,
                        occupation = new.occupation,
                        religion = new.religion,
                    )
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}