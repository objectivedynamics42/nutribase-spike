package com.objectivedynamics.nutribase.api

import com.objectivedynamics.nutribase.models.FoodGroup

//TODO we might want to rename this since the data won't be the result of a search
data class SearchResult(
    val items:List<FoodGroup>
)
