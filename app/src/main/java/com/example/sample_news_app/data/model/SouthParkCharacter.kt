package com.example.sample_news_app.data.model

import com.google.gson.annotations.SerializedName

data class SouthParkCharacter(
    @SerializedName("data") val data: DataList
) {

    data class DataList(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("sex") val sex: String,
        @SerializedName("hair_color") val hairColor: String?,
        @SerializedName("occupation") val occupation: String?,
        @SerializedName("religion") val religion: String?
    )
}