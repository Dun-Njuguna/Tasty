package com.dunk.eats.android.presentation.recipe_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dunk.eats.android.presentation.recipe_list.components.RecipeList
import com.dunk.eats.android.theme.AppTheme
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
    onClickRecipeListItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = state.isLoading) {
        Column {
            RecipeList(
                state = state,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem,
            )
        }
    }
}