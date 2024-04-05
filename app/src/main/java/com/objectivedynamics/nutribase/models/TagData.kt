package com.objectivedynamics.nutribase.models

import com.google.gson.annotations.SerializedName

data class TagData(
    @SerializedName("Tags") val tags: List<Tag>,
)

data class Tag(
    @SerializedName("Name") val name: String,
)
