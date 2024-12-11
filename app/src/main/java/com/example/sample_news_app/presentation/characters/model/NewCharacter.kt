package com.example.sample_news_app.presentation.characters.model

data class NewCharacter(
    val id: String,
    val name: String,
    val sex: String,
    val hairColor: String?,
    val occupation: String?,
    val religion: String?
)