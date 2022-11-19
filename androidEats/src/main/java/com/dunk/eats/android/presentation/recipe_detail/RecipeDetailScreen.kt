package com.dunk.eats.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dunk.eats.android.presentation.components.RecipeImage
import com.dunk.eats.android.presentation.recipe_detail.components.RecipeView
import com.dunk.eats.android.theme.AppTheme
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.presentation.recipe_detail.RecipeDetailEvents
import com.dunk.eats.presentation.recipe_detail.RecipeDetailState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
){
    AppTheme(displayProgressBar = state.isLoading) {
        if (state.recipe  == null && state.isLoading){

        }
        else if (state.recipe  == null){
            Text(text = "Error fetching recipe")
        }
        else{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                RecipeView(
                    recipe = state.recipe!!
                )
            }
        }
    }
}

