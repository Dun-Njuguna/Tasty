package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.interactors.recipe_categories.FoodCategories

sealed class RecipeBrowseEvents {
    object LoadCategories: RecipeBrowseEvents()
    object NextPage: RecipeBrowseEvents()
    object NewSearch: RecipeBrowseEvents()
    object RemoveHeadMessageFromQueue: RecipeBrowseEvents()

    data class OnUpdateQuery(val query:String): RecipeBrowseEvents()
    data class OnSelectCategory(val foodCategories: FoodCategories): RecipeBrowseEvents()
}