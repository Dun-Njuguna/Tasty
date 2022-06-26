package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.domain.model.Recipe

data class RecipeBrowseState (
    val isLoading: Boolean = false,
    val page:Int = 1,
    val query:String = "",
    val recipes: List<Recipe> = listOf()
)