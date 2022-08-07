package com.dunk.eats.android.presentation.recipe_browse

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dunk.eats.android.presentation.recipe_browse.components.RecipeListGridView
import com.dunk.eats.android.presentation.recipe_list.components.SearchAppBar
import com.dunk.eats.android.theme.AppTheme
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseEvents
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeBrowseScreen(
    state: RecipeBrowseState,
    onTriggerEvent: (RecipeBrowseEvents) -> Unit,
    onClickRecipeBrowseItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = state.isLoading) {
        Column {
            SearchAppBar(
                query = state.query,
                onQueryChange = {
                    onTriggerEvent(RecipeBrowseEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeBrowseEvents.NewSearch)
                }
            )
            RecipeListGridView(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeBrowseEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeBrowseItem,
            )
        }
    }
}