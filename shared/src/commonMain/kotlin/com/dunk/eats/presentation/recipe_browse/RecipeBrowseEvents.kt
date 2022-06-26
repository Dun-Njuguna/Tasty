package com.dunk.eats.presentation.recipe_browse

sealed class RecipeBrowseEvents {
    object LoadCategories: RecipeBrowseEvents()
    object NextPage: RecipeBrowseEvents()
    object NewSearch: RecipeBrowseEvents()
    data class OnUpdateQuery(val query:String): RecipeBrowseEvents()
}