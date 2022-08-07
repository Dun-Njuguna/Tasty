package com.dunk.eats.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dunk.eats.datasource.network.recipeService.RecipeServiceImp.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.dunk.eats.domain.model.Recipe

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun RecipeList(
    loading:Boolean,
    recipes:List<Recipe>,
    page:Int,
    onTriggerNextPage: () -> Unit,
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
                if ((index + 1) >= page * RECIPE_PAGINATION_PAGE_SIZE &&!loading ){
                    onTriggerNextPage()
                }
                RecipeListCard(
                    recipe = recipe,
                    imageHeight = 260.dp,
                    onclick = {
                        onClickRecipeListItem(recipe.id)
                    }
                )
            }
        }
    }
}