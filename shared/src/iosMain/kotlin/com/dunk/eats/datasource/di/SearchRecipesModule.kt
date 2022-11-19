package com.dunk.eats.datasource.di

import com.dunk.eats.interactors.recipe_list.SearchRecipes

class SearchRecipesModule(
    private val networkModule: NetworkModule,
    private val cachingModule: CachingModule
) {
    val searchRecipes: SearchRecipes by lazy {
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cachingModule.recipeCache
        )
    }
}