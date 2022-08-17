package com.dunk.eats.android.presentation.recipe_browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dunk.eats.android.presentation.components.PreviewImageCard
import com.dunk.eats.android.presentation.recipe_browse.components.RecipeListGridView
import com.dunk.eats.android.presentation.recipe_list.components.SearchAppBar
import com.dunk.eats.android.theme.AppTheme
import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseEvents
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeBrowseScreen(
    state: RecipeBrowseState,
    onTriggerEvent: (RecipeBrowseEvents) -> Unit,
    getFoodCategory: (name:String) -> Category?,
    onClickRecipeBrowseItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = state.isLoading) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchAppBar(
                query = state.query,
                onQueryChange = {
                    onTriggerEvent(RecipeBrowseEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeBrowseEvents.NewSearch)
                }
            )
            if(state.query.isNotEmpty()) {
                RecipeListGridView(
                    loading = state.isLoading,
                    recipes = state.recipes,
                    page = state.page,
                    onTriggerNextPage = {
                        onTriggerEvent(RecipeBrowseEvents.NextPage)
                    },
                    onClickRecipeListItem = onClickRecipeBrowseItem,
                )
            }else{
                DisplayPopularCategories(state = state, getFoodCategory = getFoodCategory,onTriggerEvent = onTriggerEvent)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayPopularCategories(
    state: RecipeBrowseState,
    getFoodCategory: (name: String) -> Category?,
    onTriggerEvent: (RecipeBrowseEvents) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        showTitle("Top categories", this)

        itemsIndexed(
            items = state.popularCategories,
        ) { index, item ->
            PreviewImageCard(
                imageUrl = item.imageUrl,
                title = item.categoryName,
                imageHeight = 100.dp,
                onclick = {
                    val category = getFoodCategory(item.categoryName)
                    category?.let {
                        onTriggerEvent(RecipeBrowseEvents.OnSelectCategory(category))
                    }
                },
            )
        }

        showTitle("All categories", this)

        itemsIndexed(items = state.popularCategories) { index, item ->
            PreviewImageCard(
                imageUrl = item.imageUrl,
                title = item.categoryName,
                imageHeight = 100.dp,
                onclick = {
                    val category = getFoodCategory(item.categoryName)
                    category?.let {
                        onTriggerEvent(RecipeBrowseEvents.OnSelectCategory(category))
                    }
                },
            )
        }
    }
}


fun showTitle(title: String, lazyGridScope: LazyGridScope) {
    return lazyGridScope.item(
        span = {
            GridItemSpan(maxLineSpan)
        }
    ) {
        Text(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp),
            text = title,
            style = MaterialTheme.typography.h3
        )
    }
}
