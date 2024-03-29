package com.dunk.eats.presentation.recipe_detail

import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.Queue

data class RecipeDetailState(
    val isLoading:Boolean = false,
    val recipe: Recipe? = null,
    val errorQueue: Queue<ErrorMessage> = Queue(mutableListOf())
){
    constructor() : this (
        isLoading =false,
        recipe = null,
        errorQueue =  Queue(mutableListOf())
    )
}
