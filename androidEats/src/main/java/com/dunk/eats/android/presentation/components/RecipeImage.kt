package com.dunk.eats.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
    imageHeight: Dp = 260.dp,
    isCircle: Boolean = false
) {
    val modifier = Modifier
        .height(imageHeight)

    if (isCircle) {
        modifier
            .size(imageHeight)
            .clip(CircleShape)
    }else{
        modifier
            .padding(10.dp)
            .fillMaxWidth()
    }

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = if (isCircle)ContentScale.Fit else ContentScale.Crop,
        modifier = modifier,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator()
            }
        } else {
            SubcomposeAsyncImageContent()
        }
    }

}