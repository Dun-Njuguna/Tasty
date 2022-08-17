package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.interactors.recipe_categories.Category

data class RecipeBrowseState (
    val isLoading: Boolean = false,
    val page:Int = 1,
    val query:String = "",
    val popularCategories: List<Category> = listOf(),
    val selectedCategory: Category? = null,
    val recipes: List<Recipe> = listOf()
)