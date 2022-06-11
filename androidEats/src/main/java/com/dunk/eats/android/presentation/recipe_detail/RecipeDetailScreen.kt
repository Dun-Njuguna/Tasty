package com.dunk.eats.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dunk.eats.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
){
    if (recipe == null){
        Text(text = "Error")
    }else{
        Text(text = "recipe id ${recipe.title}")
        Text(text = "recipe id ${recipe.ingredients}")
    }
}

