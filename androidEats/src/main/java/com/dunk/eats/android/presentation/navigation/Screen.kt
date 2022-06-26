package com.dunk.eats.android.presentation.navigation

import com.dunk.eats.android.R

sealed class Screen(
    val route: String,
    val title:String,
    val icon: Int? = null,
) {
    object RecipeList: Screen(route = "recipeList", title = "Home", icon = R.drawable.ic_home_24px)
    object RecipeBrowseScreen: Screen(route = "browse", title = "Browse", icon = R.drawable.ic_manage_search_24px)

    object RecipeDetail: Screen(route = "recipeDetail", title = "Details Screen")
}

val bottomNavScreens = listOf(
    Screen.RecipeList,
    Screen.RecipeBrowseScreen
)