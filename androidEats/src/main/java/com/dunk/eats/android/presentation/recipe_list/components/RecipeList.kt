package com.dunk.eats.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.dunk.eats.domain.model.Recipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeList(
    loading:Boolean,
    recipes:List<Recipe>,
    onClickRecipeListItem:(Int) -> Unit
) {
    if (loading && recipes.isEmpty()){

    }
    else if(recipes.isEmpty()){

    }
    else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index: Int, recipe ->
                RecipeListCard(
                    recipe = recipe,
                    onclick = {
                        onClickRecipeListItem(recipe.id)
                    }
                )
            }
        }
    }
}