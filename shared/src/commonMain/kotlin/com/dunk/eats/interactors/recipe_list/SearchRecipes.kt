package com.dunk.eats.interactors.recipe_list

import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService
) {
    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page = page, query = query)
            emit(DataState.data(message = null, data = recipes))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}