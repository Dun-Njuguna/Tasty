package com.dunk.eats.presentation.recipe_detail

import com.dunk.eats.domain.model.Recipe

data class RecipeDetailState(
    val isLoading:Boolean = false,
    val recipe: Recipe? = null
)
