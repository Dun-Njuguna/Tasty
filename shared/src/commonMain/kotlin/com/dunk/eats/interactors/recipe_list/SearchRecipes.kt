package com.dunk.eats.interactors.recipe_list

import com.dunk.eats.datasource.cache.RecipeCache
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
//            val recipes = recipeService.search(page = page, query = query)
//            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()){
                recipeCache.getAll(page = page)
            }else{
                recipeCache.search(query = query, page = page)
            }
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}