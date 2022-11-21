package com.dunk.eats.presentation.recipe_browse

import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.Queue
import com.dunk.eats.interactors.recipe_categories.FoodCategories

actual data class RecipeBrowseState (
    val isLoading: Boolean = false,
    val page:Int = 1,
    val query:String = "",
    val categories: List<FoodCategories> = listOf(),
    val selectedFoodCategories: FoodCategories? = null,
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe:Recipe? = null,
    val errorQueue: Queue<ErrorMessage> = Queue(mutableListOf())
){
    // building zero argument constructor for swift ui
    constructor(): this(
        isLoading = false,
        page = 1,
        query= "",
        categories = listOf(),
        selectedFoodCategories = null,
        recipes= listOf(),
        bottomRecipe = null,
        errorQueue= Queue(mutableListOf())
    )
}