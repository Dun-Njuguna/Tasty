package com.dunk.eats.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.RecipeImage
import com.dunk.eats.domain.model.Recipe

@ExperimentalMaterialApi
@Composable
fun RecipeListCard(
    recipe: Recipe,
    onclick: ()-> Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        onClick = onclick,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .height(220.dp)
        ) {
            RecipeImage(imageHeight = 120.dp, url = recipe.featuredImage, contentDescription = recipe.title)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = recipe.rating.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically)
                    ,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}