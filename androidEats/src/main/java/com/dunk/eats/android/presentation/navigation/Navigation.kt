package com.dunk.eats.android.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dunk.eats.android.presentation.recipe_browse.RecipeBrowseScreen
import com.dunk.eats.android.presentation.recipe_browse.RecipeBrowseViewModel
import com.dunk.eats.android.presentation.recipe_detail.RecipeDetailScreen
import com.dunk.eats.android.presentation.recipe_detail.RecipeDetailViewModel
import com.dunk.eats.android.presentation.recipe_list.ListRecipeScreen
import com.dunk.eats.android.presentation.recipe_list.RecipeListViewModel
import com.dunk.eats.android.theme.AppTheme

@OptIn(
    ExperimentalStdlibApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = rememberSaveable { mutableStateOf(true) }
    showBottomBar.value = when (navBackStackEntry?.destination?.route) {
        Screen.RecipeDetail.route + "/{recipeId}" -> false
        else -> true
    }

    AppTheme(displayProgressBar = false) {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = showBottomBar.value,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    BuildBottomNav(navController, currentDestination)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.RecipeList.route,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
                    val viewModel: RecipeListViewModel = hiltViewModel()
                    ListRecipeScreen(
                        state = viewModel.state.value,
                        onTriggerEvent = viewModel::onTriggerEvent,
                        onClickRecipeListItem = { recipeId ->
                            navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                        }
                    )
                }
                composable(
                    route = Screen.RecipeBrowseScreen.route,
                ) { navBackStackEntry ->
                    val viewModel: RecipeBrowseViewModel = hiltViewModel()
                    RecipeBrowseScreen(
                        state = viewModel.state.value,
                        onTriggerEvent = viewModel::onTriggerEvent,
                        onClickRecipeBrowseItem = { recipeId ->
                            navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                        }
                    )
                }

                composable(
                    route = Screen.RecipeDetail.route + "/{recipeId}",
                    arguments = listOf(navArgument(name = "recipeId") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val viewModel: RecipeDetailViewModel = hiltViewModel()
                    RecipeDetailScreen(recipe = viewModel.recipe.value)
                }
            }
        }
    }
}

@Composable
fun BuildBottomNav(navController: NavHostController, currentDestination: NavDestination?) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        bottomNavScreens.forEach { screen ->
            screen.icon?.let { image ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    icon = {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = screen.route
                        )
                    },
                    label = { Text(screen.title) },
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onPrimary,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // re-selecting the same item
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun BottomNavItem(
    title:String,
    selected:Boolean,
    imageResource:Int,
    description:String,
    onclick:() -> Unit,
){
    val backgroundColor = if (selected){MaterialTheme.colors.primary}else{MaterialTheme.colors.onPrimary}
    val contentColor = if (selected){MaterialTheme.colors.onPrimary}else{MaterialTheme.colors.primary}
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onclick)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = imageResource),
                contentDescription = description,
                tint =  contentColor
            )
            AnimatedVisibility(visible = selected) {
                 Text(text = title, style = MaterialTheme.typography.h6)
            }
        }
    }
}