package com.example.sample_news_app.data

import com.example.sample_news_app.data.model.SouthPark
import retrofit2.Response
import retrofit2.http.GET

interface SouthParkAPI {

    @GET("https://spapi.dev/api/characters")
    suspend fun getSP(): Response<SouthPark>

//    @GET("https://spapi.dev/api/characters/{$CHARACTER_ID_KEY}")
//    suspend fun getCharacter(@Path(CHARACTER_ID_KEY)id: String): Response<SouthParkCharacter>
}