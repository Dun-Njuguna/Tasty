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
import com.dunk.eats.interactors.recipe_categories.FoodCategories

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryChip(
    foodCategories: FoodCategories,
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
                onSelected(foodCategories.categoryName)
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
                ShowCategoryTitle(foodCategories = foodCategories)
                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    DisplayCategoryImage(foodCategories)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .width(chipWidth)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DisplayCategoryImage(foodCategories)
                Spacer(modifier = Modifier.padding(3.dp))
                ShowCategoryTitle(foodCategories = foodCategories)
            }
        }
    }
}

@Composable
private fun DisplayCategoryImage(foodCategories: FoodCategories) {
    RecipeImage(
        url = foodCategories.imageUrl,
        contentDescription = foodCategories.name,
        imageHeight = 50.dp,
        isCircle = true
    )
}

@Composable
private fun ShowCategoryTitle(foodCategories: FoodCategories) {
    Text(
        text = foodCategories.name,
        style = MaterialTheme.typography.body2,
        color = Color.Black
    )
}

