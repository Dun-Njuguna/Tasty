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

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
){
    AppTheme(displayProgressBar = false) {
        if (recipe == null){
            Text(text = "Error")
        }else{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                RecipeView(
                    recipe = recipe
                )
            }
        }
    }
}

