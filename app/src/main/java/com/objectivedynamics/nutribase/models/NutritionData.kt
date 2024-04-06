package com.objectivedynamics.nutribase.models

import com.google.gson.annotations.SerializedName

data class NutritionData(
    @SerializedName("Tags") val tags: List<Tag>,
)

data class Tag(
    @SerializedName("Name") val name: String,
)
