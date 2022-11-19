package com.dunk.eats.presentation.recipe_detail

sealed class RecipeDetailEvents{
    object RemoveHeadMessageFromQueue: RecipeDetailEvents()

    data class GetRecipe(val recipeId:Int): RecipeDetailEvents()
}
