package com.objectivedynamics.nutribase.models

import com.google.gson.annotations.SerializedName

data class TagData(
    @SerializedName("Tags") val tags: List<Tag>,
//TODO - move to foods model    @SerializedName("Foods") val foods: List<Food>
)

/*
//TODO - move to foods model
data class Food(
    @SerializedName("Name") val name: String,
    @SerializedName("KPer100") val kPer100: Double,
    @SerializedName("Tags") val tags: List<String>
)
*/

data class Tag(
    @SerializedName("Name") val name: String,
)
