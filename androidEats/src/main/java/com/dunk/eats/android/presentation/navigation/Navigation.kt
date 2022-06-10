package com.dunk.eats.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dunk.eats.android.presentation.recipe_detail.RecipeDetailScreen
import com.dunk.eats.android.presentation.recipe_detail.RecipeDetailViewModel
import com.dunk.eats.android.presentation.recipe_list.ListRecipeScreen
import com.dunk.eats.android.presentation.recipe_list.RecipeListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){
        composable(route = Screen.RecipeList.route){ navBackStackEntry ->
            val viewModel:RecipeListViewModel =  hiltViewModel()
            ListRecipeScreen(onSelectRecipe = { recipeId ->
                navController.navigate(Screen.RecipeDetail.route + "/$recipeId" )
            })
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments =  listOf(navArgument(name = "recipeId"){
                type = NavType.IntType
            })
        ){ navBackStackEntry ->
            val viewModel:RecipeDetailViewModel =  hiltViewModel()
            RecipeDetailScreen(recipeId = viewModel.recipeId.value)
        }
    }
}