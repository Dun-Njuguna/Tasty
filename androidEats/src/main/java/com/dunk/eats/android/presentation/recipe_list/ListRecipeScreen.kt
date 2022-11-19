package com.dunk.eats.android.presentation.recipe_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.recipe_list.components.RecipeList
import com.dunk.eats.android.theme.AppTheme
import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.presentation.recipe_list.RecipeListEvents
import com.dunk.eats.presentation.recipe_list.RecipeListState

@OptIn(
    ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)
@Composable
fun ListRecipeScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    getFoodCategory: (name:String) -> Category?,
    onClickRecipeListItem: (Int) -> Unit
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.errorQueue
    ) {
        Column(
            modifier = Modifier.padding(top = 5.dp)
        ) {
            RecipeList(
                state = state,
                onTriggerEvent = onTriggerEvent,
                onClickRecipeListItem = onClickRecipeListItem,
                getFoodCategory,
            )
        }
    }
}