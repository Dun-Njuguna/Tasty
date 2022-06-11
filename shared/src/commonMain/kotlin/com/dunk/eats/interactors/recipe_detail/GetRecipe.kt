package com.dunk.eats.interactors.recipe_detail

import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeService: RecipeService
) {
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.get(recipeId)
            emit(DataState.data(message = null, data = recipes))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}