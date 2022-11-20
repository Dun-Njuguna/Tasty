package com.dunk.eats.presentation.recipe_list

import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.Queue
import com.dunk.eats.interactors.recipe_categories.FoodCategories

actual data class RecipeListState (
    val isLoading: Boolean = false,
    val page:Int = 1,
    val query:String = "",
    val recipes: List<Recipe> = listOf(),
    val topCategories: List<FoodCategories> = listOf(),
    val newCategories: List<FoodCategories> = listOf(),
    val selectedFoodCategories: FoodCategories? = null,
    val errorQueue: Queue<ErrorMessage> = Queue(mutableListOf())
)