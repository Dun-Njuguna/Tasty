package com.dunk.eats.android.di

import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.interactors.recipe_detail.GetRecipe
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorsModule {
    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService
    ): SearchRecipes{
        return SearchRecipes(recipeService = recipeService)
    }

    @Singleton
    @Provides
    fun provideGetRecipes(
        recipeService: RecipeService
    ): GetRecipe{
        return GetRecipe(recipeService = recipeService)
    }
}