package com.dunk.eats.android.presentation.recipe_browse.components


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.PreviewImageCard
import com.dunk.eats.datasource.network.recipeService.RecipeServiceImp.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.dunk.eats.domain.model.Recipe

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun RecipeListGridView(
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
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 145.dp)
        ) {
            itemsIndexed(items = recipes) { index: Int, recipe ->
                if ((index + 1) >= page * RECIPE_PAGINATION_PAGE_SIZE && !loading) {
                    onTriggerNextPage()
                }
                PreviewImageCard(
                    imageUrl = recipe.featuredImage,
                    title = recipe.title,
                    rating = recipe.rating,
                    onclick = {
                        onClickRecipeListItem(recipe.id)
                    }
                )
            }
        }
    }
}