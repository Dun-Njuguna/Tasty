package com.dunk.eats.interactors.recipe_list

import com.dunk.eats.datasource.cache.RecipeCache
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.model.UIComponentType
import com.dunk.eats.domain.util.DataState
import com.dunk.eats.domain.util.FlowHelper
import com.dunk.eats.domain.util.asFlowHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    fun execute(
        page: Int,
        query: String
    ): FlowHelper<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            if (query == "error"){
                throw Exception("Clicked on error foodCategories")
            }
            val recipes = recipeService.search(page = page, query = query)
            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()){
                recipeCache.getAll(page = page)
            }else{
                recipeCache.search(query = query, page = page)
            }
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(
                DataState.error(
                message = ErrorMessage.Builder()
                    .id("SearchRecipe.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message ?: "Unknown error")
                    .build()
                )
            )
        }
    }.asFlowHelper()

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}