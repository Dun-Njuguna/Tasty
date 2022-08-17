package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.presentation.recipe_list.RecipeListEvents

sealed class RecipeBrowseEvents {
    object LoadCategories: RecipeBrowseEvents()
    object NextPage: RecipeBrowseEvents()
    object NewSearch: RecipeBrowseEvents()
    data class OnUpdateQuery(val query:String): RecipeBrowseEvents()
    data class OnSelectCategory(val category: Category): RecipeBrowseEvents()
}