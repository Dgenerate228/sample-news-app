package com.example.sample_news_app.data

import com.example.sample_news_app.data.model.SouthPark
import com.example.sample_news_app.data.model.SouthParkCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SouthParkAPI {

    @GET("https://spapi.dev/api/characters")
    suspend fun getSP(): Response<SouthPark>

    @GET("https://spapi.dev/api/characters/{id}")
    suspend fun getCharacter(@Path("id")id: String): Response<SouthParkCharacter>
}