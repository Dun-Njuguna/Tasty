package com.dunk.eats.presentation.recipe_detail

import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.Queue

data class RecipeDetailState(
    val isLoading:Boolean = false,
    val recipe: Recipe? = null,
    val errorQueue: Queue<String> = Queue(mutableListOf())
)
