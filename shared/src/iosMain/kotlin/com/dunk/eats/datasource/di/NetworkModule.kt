package com.dunk.eats.datasource.di

import com.dunk.eats.datasource.network.KtorClientFactory
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.datasource.network.recipeService.RecipeServiceImp

class NetworkModule {
    val recipeService: RecipeService by lazy {
        RecipeServiceImp(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImp.BASE_URL
        )
    }
}