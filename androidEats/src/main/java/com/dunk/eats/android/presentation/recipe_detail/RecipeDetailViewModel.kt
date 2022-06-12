package com.dunk.eats.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DatetimeUtil
import com.dunk.eats.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId = recipeId)
        }
    }

    private fun getRecipe(recipeId:Int){
        println("Recipe list view model:..id.....${recipeId}")
        getRecipe.execute(
            recipeId = recipeId
        ).onEach { dataState ->
            dataState.data?.let {
                recipe.value = it
                println("Recipe list view model:.......${it.title}")
                println("Recipe list view model:.......${it.ingredients}")
                println("Recipe list view model:.......${DatetimeUtil().humanizeDatetime(it.dateAdded)}")
            }

            println("Recipe list view model:......loading${dataState.isLoading}...message${dataState.message }...data${dataState.data}")
        }.launchIn(viewModelScope)
    }

}