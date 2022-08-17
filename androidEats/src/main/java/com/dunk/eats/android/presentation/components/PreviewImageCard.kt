package com.dunk.eats.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.RecipeImage
import com.dunk.eats.domain.model.Recipe

@ExperimentalMaterialApi
@Composable
fun PreviewImageCard(
    imageUrl:String,
    title:String,
    rating: Int? = null,
    imageHeight: Dp = 120.dp,
    onclick: ()-> Unit
){
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        onClick = onclick,
        elevation = 8.dp
    ) {
        Column{
            RecipeImage(imageHeight = imageHeight, url = imageUrl, contentDescription = title)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp)
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(if (rating == null) Alignment.CenterHorizontally else Alignment.Start),
                    style = MaterialTheme.typography.h5
                )
                rating?.let {
                    Text(
                        text = rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}