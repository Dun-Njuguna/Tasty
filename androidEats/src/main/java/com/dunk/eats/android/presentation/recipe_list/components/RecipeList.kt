package com.dunk.eats.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.recipe_category.CategoryChip

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.dunk.eats.android.presentation.components.PreviewImageCard
import com.dunk.eats.datasource.network.recipeService.RecipeServiceImp.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.presentation.recipe_list.RecipeListEvents
import com.dunk.eats.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun RecipeList(
    state:RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem:(Int) -> Unit,
    getFoodCategory: (name:String) -> Category?,
) {
    if (state.isLoading && state.recipes.isEmpty()){

    }
    else if(state.recipes.isEmpty()){

    }
    else {
        LazyColumn {
            item {
                DisplayCategoryChips(state, getFoodCategory, onTriggerEvent)
            }
            itemsIndexed(
                items = state.recipes
            ) { index: Int, recipe ->
                if ((index + 1) >= state.page * RECIPE_PAGINATION_PAGE_SIZE &&!state.isLoading ){
                    onTriggerEvent(RecipeListEvents.NextPage)
                }
                PreviewImageCard(
                    imageUrl = recipe.featuredImage,
                    title = recipe.title,
                    rating = recipe.rating,
                    imageHeight = 260.dp,
                    onclick = {
                        onClickRecipeListItem(recipe.id)
                    },
                )
            }
        }
    }
}

@Composable
fun DisplayCategoryChips(
    state: RecipeListState,
    getFoodCategory: (name: String) -> Category?,
    onTriggerEvent: (RecipeListEvents) -> Unit
) {
    LazyHorizontalGrid(
        modifier = Modifier.height(85.dp),
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = state.newCategories) { index, item ->
            CategoryChip(
                category = item,
                isTopLevel = true,
                isHorizontal = true,
                chipWidth = 150.dp,
                onSelected = {
                    val category = getFoodCategory(it)
                    category?.let {
                        onTriggerEvent(RecipeListEvents.OnSelectCategory(category))
                    }
                })
        }
    }
    Spacer(modifier = Modifier.padding(vertical = 5.dp))

    LazyHorizontalGrid(
        modifier = Modifier.height(120.dp),
        rows = GridCells.Fixed(1),
    ) {
        itemsIndexed(items = state.topCategories) { index, item ->
            CategoryChip(
                category = item,
                isTopLevel = false,
                chipWidth = 100.dp,
                onSelected = {
                    val category = getFoodCategory(it)
                    category?.let {
                        onTriggerEvent(RecipeListEvents.OnSelectCategory(category))
                    }
                })
        }
    }

    Spacer(modifier = Modifier.padding(vertical = 5.dp))
}
