package com.dunk.eats.presentation.recipe_list

import com.dunk.eats.interactors.recipe_categories.FoodCategories

sealed class RecipeListEvents {
    object LoadRecipes: RecipeListEvents()
    object NextPage: RecipeListEvents()
    object NewSearch: RecipeListEvents()
    object RemoveHeadMessageFromQueue: RecipeListEvents()

    data class OnUpdateQuery(val query:String):RecipeListEvents()

    data class OnSelectCategory(val foodCategories: FoodCategories): RecipeListEvents()
}
