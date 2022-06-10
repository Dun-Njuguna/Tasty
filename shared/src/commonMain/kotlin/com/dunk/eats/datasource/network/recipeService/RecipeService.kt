package com.dunk.eats.datasource.network.recipeService

import com.dunk.eats.domain.model.Recipe


interface RecipeService  {
    suspend fun search(
        page:Int,
        query: String
    ): List<Recipe>

    suspend fun get(
        id:Int
    ): Recipe
}