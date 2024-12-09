package com.example.sample_news_app.data.model

import com.google.gson.annotations.SerializedName

data class SouthPark(
    @SerializedName("data") val dataList: List<DataList>
) {

    data class DataList(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("sex") val sex: String,
        @SerializedName("hair_color") val hairColor: String,
        @SerializedName("occupation") val occupation: String,
        @SerializedName("religion") val religion: String
    )
}
