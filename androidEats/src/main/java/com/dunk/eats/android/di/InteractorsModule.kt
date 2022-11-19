package com.dunk.eats.android.di

import com.dunk.eats.datasource.cache.RecipeCache
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.interactors.recipe_categories.CategoryTypes
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
        recipeService: RecipeService,
        recipeCache: RecipeCache
    ): SearchRecipes{
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipes(
        recipeCache: RecipeCache
    ): GetRecipe{
        return GetRecipe(recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideCategoryUtil(): CategoryTypes{
        return CategoryTypes()
    }
}