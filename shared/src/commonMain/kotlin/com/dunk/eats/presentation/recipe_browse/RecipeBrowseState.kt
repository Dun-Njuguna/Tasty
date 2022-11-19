package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.Queue
import com.dunk.eats.interactors.recipe_categories.Category

data class RecipeBrowseState (
    val isLoading: Boolean = false,
    val page:Int = 1,
    val query:String = "",
    val categories: List<Category> = listOf(),
    val selectedCategory: Category? = null,
    val recipes: List<Recipe> = listOf(),
    val errorQueue: Queue<ErrorMessage> = Queue(mutableListOf())
)