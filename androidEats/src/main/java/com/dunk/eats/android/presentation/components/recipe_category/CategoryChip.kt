package com.dunk.eats.android.presentation.components.recipe_category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.RecipeImage
import com.dunk.eats.interactors.recipe_categories.Category

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryChip(
    category: Category,
    isTopLevel: Boolean = false,
    isHorizontal: Boolean = false,
    isSelected: Boolean = false,
    chipWidth: Dp = 120.dp,
    onSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onSelected(category.categoryName)
            },
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray.copy(alpha = 0.3f)
    ) {
        if (isHorizontal) {
            Row(
                modifier = Modifier
                    .width(chipWidth)
                    .padding(start = 8.dp, bottom = 10.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                ShowCategoryTitle(category = category)
                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    DisplayCategoryImage(category)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .width(chipWidth)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DisplayCategoryImage(category)
                Spacer(modifier = Modifier.padding(3.dp))
                ShowCategoryTitle(category = category)
            }
        }
    }
}

@Composable
private fun DisplayCategoryImage(category: Category) {
    RecipeImage(
        url = category.imageUrl,
        contentDescription = category.name,
        imageHeight = 50.dp,
        isCircle = true
    )
}

@Composable
private fun ShowCategoryTitle(category: Category) {
    Text(
        text = category.name,
        style = MaterialTheme.typography.body2,
        color = Color.Black
    )
}

