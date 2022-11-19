package com.dunk.eats.datasource.di

import com.dunk.eats.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val cachingModule: CachingModule
) {
    val getRecipe: GetRecipe by lazy {
        GetRecipe(
            recipeCache = cachingModule.recipeCache
        )
    }
}