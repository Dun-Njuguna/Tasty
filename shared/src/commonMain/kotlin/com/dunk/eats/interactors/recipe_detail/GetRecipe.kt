package com.dunk.eats.interactors.recipe_detail

import com.dunk.eats.datasource.cache.RecipeCache
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.model.UIComponentType
import com.dunk.eats.domain.util.DataState
import com.dunk.eats.domain.util.FlowHelper
import com.dunk.eats.domain.util.asFlowHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache
) {
    fun execute(
        recipeId: Int,
    ): FlowHelper<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            val cacheResult = recipeCache.get(recipeId)
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(
                DataState.error(
                    message = ErrorMessage.Builder()
                        .id("GetRecipe.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown error")
                        .build()
                )
            )
        }
    }.asFlowHelper()
}