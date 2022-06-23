package com.dunk.eats.android.presentation.recipe_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dunk.eats.android.presentation.recipe_list.components.RecipeList
import com.dunk.eats.android.theme.AppTheme
import com.dunk.eats.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun ListRecipeScreen(
    state: RecipeListState,
    onClickRecipeListItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = state.isLoading) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem,
        )
    }
}