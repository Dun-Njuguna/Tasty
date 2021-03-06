package com.dunk.eats.presentation.recipe_list

sealed class RecipeListEvents{
    object LoadRecipes: RecipeListEvents()
    object NextPage: RecipeListEvents()
    object NewSearch: RecipeListEvents()

    data class OnUpdateQuery(val query:String):RecipeListEvents()
}
